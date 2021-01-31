package isa.apoteka.service;

import java.util.List;
import isa.apoteka.domain.Address;
import isa.apoteka.dto.CityDTO;

public interface AddressService {
    public List<Address> findAllAddressesForCity (CityDTO city);
    public void update(String street, Long cityId, Long addressId);
    
    public Address insertNewAddress(Address address);
}
