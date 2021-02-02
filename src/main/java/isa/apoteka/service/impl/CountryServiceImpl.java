package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import isa.apoteka.domain.Country;
import isa.apoteka.repository.CountryRepository;
import isa.apoteka.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	//@Autowired
	//private AuthorityService authService;
	
	@Override
	public List<Country> findAllCountries() throws AccessDeniedException {
		List<Country> result = countryRepository.findAllCountries();
		return result;
	}
}
