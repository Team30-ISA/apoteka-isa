package isa.apoteka.service.impl;

import isa.apoteka.domain.*;
import isa.apoteka.repository.SupplierRepository;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.CityService;
import isa.apoteka.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceIml implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

    @Override
    public UserRequest update(UserRequest userRequest) throws Exception {
        if(!((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().equals(userRequest.getId()))
            throw new Exception("Unauthorized");

        userRequest.updateValidation();
        Supplier user = supplierRepository.getOne(userRequest.getId());

        user.setFirstName(userRequest.getFirstname());
        user.setLastName(userRequest.getLastname());
        user.setPhonenumber(userRequest.getPhoneNumber());

        if(!user.getAddress().getStreet().equals(userRequest.getAddress())) {
            City city = cityService.findCityById(userRequest.getCityId().longValue());
            Address address = new Address(userRequest.getAddress(), city);
            user.setAddress(address);
            addressService.insertNewAddress(address);
        } else {
            user.setAddress(user.getAddress());
        }

        supplierRepository.save(user);
        return userRequest;
    }
    
    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }
}
