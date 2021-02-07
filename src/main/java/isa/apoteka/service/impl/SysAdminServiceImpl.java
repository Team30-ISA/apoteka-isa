package isa.apoteka.service.impl;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.repository.UserRepository;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.CityService;
import isa.apoteka.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

    @Override
    public User update(UserRequest userRequest) throws Exception {
        if(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId() != userRequest.getId())
            throw new Exception("Unauthorized");

        userRequest.updateValidation();
        User oldUser = userRepository.getOne(userRequest.getId());

        User user = new User(userRequest);
        user.setEnabled(true);
        user.setPasswordForReset(oldUser.getPassword());
        user.setEmail(oldUser.getEmail());
        user.setUsername(oldUser.getUsername());
        user.setLastPasswordResetDate(oldUser.getLastPasswordResetDate());

        if(!oldUser.getAddress().getStreet().equals(userRequest.getAddress())) {
            City city = cityService.findCityById(userRequest.getCityId().longValue());
            Address address = new Address(userRequest.getAddress(), city);
            user.setAddress(address);
            addressService.insertNewAddress(address);
        } else {
            user.setAddress(oldUser.getAddress());
        }

        userRepository.save(user);
        return user;
    }
}
