package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.Authority;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Supplier;
import isa.apoteka.domain.SystemAdmin;
import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.repository.DermatologistRepository;
import isa.apoteka.repository.SupplierRepository;
import isa.apoteka.repository.SysAdminRepository;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.CityService;
import isa.apoteka.service.SysAdminService;

@Service
public class SysAdminServiceImpl implements SysAdminService {

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

    @Autowired
    private SysAdminRepository sysAdminRepository;

    @Override
    public UserRequest update(UserRequest userRequest) throws Exception {
        if(!((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().equals(userRequest.getId()))
            throw new Exception("Unauthorized");

        userRequest.updateValidation();
        SystemAdmin user = sysAdminRepository.getOne(userRequest.getId());

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

        sysAdminRepository.save(user);
        return userRequest;
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
    public SystemAdmin create(PharmacistDTO userRequest) {
        Address address = this.createNewAddress(userRequest.getCityId(), userRequest.getAddressString());
        userRequest.setAddress(address);
        SystemAdmin sysAdmin = this.createNewSysAdmin(userRequest);
        sysAdminRepository.save(sysAdmin);
        return sysAdmin;
    }

    private Address createNewAddress(Long cityId, String address) {
        City city = cityService.findCityById(cityId);
        Address newAddress = new Address(address, city);
        return addressService.insertNewAddress(newAddress);
    }

    private SystemAdmin createNewSysAdmin(PharmacistDTO sysAdminData) {
        SystemAdmin user = new SystemAdmin(sysAdminData);
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
