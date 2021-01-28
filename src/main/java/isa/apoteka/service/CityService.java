package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;

public interface CityService {
    List<City> findAllCitiesForCountry(Country country);
}
