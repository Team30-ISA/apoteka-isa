package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;
import isa.apoteka.dto.CityDTO;

public interface AddressService {
    List<Address> findAllAddressesForCity (CityDTO city);
    void update(String street, Long cityId, Long addressId);
}
