package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.Authority;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.repository.PharmacyAdminRepository;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.CityService;
import isa.apoteka.service.PharmacyAdminService;
import isa.apoteka.service.PharmacyService;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService {

    @Autowired
    private PharmacyAdminRepository pharmacyAdminRepository;

    @Autowired
    private PharmacyService pharmacyService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void update(String firstName, String lastName, Long id) {
        pharmacyAdminRepository.update(firstName, lastName, id);
    }

    @Override
    public PharmacyAdmin create(PharmacistDTO pharmacyAdminData) {
        Address address = this.createNewAddress(pharmacyAdminData.getCityId(), pharmacyAdminData.getAddressString());
        pharmacyAdminData.setAddress(address);
        PharmacyAdmin pharmacyAdmin = this.createNewPharmacyAdmin(pharmacyAdminData);
        pharmacyAdminRepository.save(pharmacyAdmin);
        return pharmacyAdmin;
    }

    private Address createNewAddress(Long cityId, String address) {
        City city = cityService.findCityById(cityId);
        Address newAddress = new Address(address, city);
        return addressService.insertNewAddress(newAddress);
    }

    private PharmacyAdmin createNewPharmacyAdmin(PharmacistDTO pharmacyAdminData) {
        Pharmacy pharmacy = pharmacyService.findOne(pharmacyAdminData.getPharmacyId());
        PharmacyAdmin pharmacyAdmin = new PharmacyAdmin(pharmacyAdminData, pharmacy);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        pharmacyAdmin.setPasswordForReset(passwordEncoder.encode(pharmacyAdminData.getPassword()));
        //pharmacyAdmin.setPasswordForReset(pharmacyAdminData.getPassword());
        List<Authority> auth = authorityService.findByname("ROLE_ADMIN");
        pharmacyAdmin.setAuthorities(auth);
        return pharmacyAdmin;
    }
}
