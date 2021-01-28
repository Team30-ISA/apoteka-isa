package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;
import isa.apoteka.repository.AddressRepository;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.AuthorityService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public List<Address> findAllAddressesForCity(City city) throws AccessDeniedException {
		Long id = city.getId();
		List<Address> result = addressRepository.findAllAddressesForCity(id);
		return result;
	}
}
