package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;
import isa.apoteka.dto.CountryDTO;

public interface CityService {
    List<City> findAllCitiesForCountry(CountryDTO country);
}