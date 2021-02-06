package isa.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.apoteka.domain.Country;
import isa.apoteka.service.CountryService;

@RestController
@RequestMapping(value = "/api/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/getAllCountries")
	public List<Country> loadAll() {
		return this.countryService.findAllCountries();
	}
}
