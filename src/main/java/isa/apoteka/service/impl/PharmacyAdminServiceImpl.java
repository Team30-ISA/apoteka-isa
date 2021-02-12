package isa.apoteka.service.impl;

import isa.apoteka.domain.*;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.repository.PharmacyRepository;
import isa.apoteka.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import isa.apoteka.repository.PharmacyAdminRepository;

import java.util.List;

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
        //pharmacyAdmin.setPasswordForReset(passwordEncoder.encode(pharmacyAdminData.getPassword()));
        pharmacyAdmin.setPasswordForReset(pharmacyAdminData.getPassword());
        List<Authority> auth = authorityService.findByname("ROLE_ADMIN");
        pharmacyAdmin.setAuthorities(auth);
        return pharmacyAdmin;
    }
}
