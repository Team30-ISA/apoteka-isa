package isa.apoteka.service.impl;

import isa.apoteka.domain.*;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.repository.DermatologistRepository;
import isa.apoteka.repository.SupplierRepository;
import isa.apoteka.repository.UserRepository;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.CityService;
import isa.apoteka.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAdminServiceImpl implements SysAdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private SupplierRepository supplierRepository;

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

    @Override
    public Supplier createSupplier(PharmacistDTO userRequest) {
        Address address = this.createNewAddress(userRequest.getCityId(), userRequest.getAddressString());
        userRequest.setAddress(address);
        Supplier supplier = this.createNewSupplier(userRequest);
        supplierRepository.save(supplier);
        return supplier;
    }

    @Override
    public Dermatologist createDermatologist(PharmacistDTO userRequest) {
        Address address = this.createNewAddress(userRequest.getCityId(), userRequest.getAddressString());
        userRequest.setAddress(address);
        Dermatologist dermatologist = this.createNewDermatologist(userRequest);
        dermatologistRepository.save(dermatologist);
        return dermatologist;
    }

    @Override
    public User create(PharmacistDTO userRequest) {
        Address address = this.createNewAddress(userRequest.getCityId(), userRequest.getAddressString());
        userRequest.setAddress(address);
        User sysAdmin = this.createNewSysAdmin(userRequest);
        userRepository.save(sysAdmin);
        return sysAdmin;
    }

    private Address createNewAddress(Long cityId, String address) {
        City city = cityService.findCityById(cityId);
        Address newAddress = new Address(address, city);
        return addressService.insertNewAddress(newAddress);
    }

    private User createNewSysAdmin(PharmacistDTO sysAdminData) {
        User user = new User(sysAdminData);
        List<Authority> auth = authorityService.findByname("ROLE_SYS_ADMIN");
        user.setPasswordForReset(passwordEncoder.encode(sysAdminData.getPassword()));
        user.setAuthorities(auth);
        return user;
    }

    private Dermatologist createNewDermatologist(PharmacistDTO dermatologistData) {
        Dermatologist dermatologist= new Dermatologist(dermatologistData);
        List<Authority> auth = authorityService.findByname("ROLE_DERM");
        dermatologist.setPasswordForReset(passwordEncoder.encode(dermatologistData.getPassword()));
        dermatologist.setAuthorities(auth);
        return dermatologist;
    }

    private Supplier createNewSupplier(PharmacistDTO supplierData) {
        Supplier supplier = new Supplier(supplierData);
        List<Authority> auth = authorityService.findByname("ROLE_SUPL");
        supplier.setPasswordForReset(passwordEncoder.encode(supplierData.getPassword()));
        supplier.setAuthorities(auth);
        return supplier;
    }
}
