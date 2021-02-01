package isa.apoteka.service;

import java.util.List;
import isa.apoteka.domain.City;
import isa.apoteka.dto.CountryDTO;

public interface CityService {
    List<City> findAllCitiesForCountry(CountryDTO country);
    City findCityById(Long id);
}
