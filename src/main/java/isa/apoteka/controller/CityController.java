package isa.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.CityService;

@RestController
@RequestMapping(value = "/api/city", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping("/getAllCitiesForCountry")
	//@PreAuthorize("hasRole('PATIENT')")
	public List<City> loadAll(Country country) {
		return this.cityService.findAllCitiesForCountry(country);
	}
}
