package isa.apoteka.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;
import isa.apoteka.repository.CityRepository;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	//@Autowired
	//private AuthorityService authService;
	
	@Override
	public List<City> findAllCitiesForCountry(Country country) throws AccessDeniedException {
		Long id = country.getId();
		List<City> result = cityRepository.findAllCitiesForCountry(id);
		return result;
	}
}

