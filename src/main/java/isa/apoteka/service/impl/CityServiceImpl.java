package isa.apoteka.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import isa.apoteka.domain.City;
import isa.apoteka.dto.CountryDTO;
import isa.apoteka.repository.CityRepository;
import isa.apoteka.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public List<City> findAllCitiesForCountry(CountryDTO country) throws AccessDeniedException {
		Long id = country.getId();
		List<City> result = cityRepository.findAllCitiesForCountry(id);
		return result;
	}

	@Override
	public City findCityById(Long id) {
		return cityRepository.findById(id).orElse(null);
	}
}

