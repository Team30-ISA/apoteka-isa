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
import isa.apoteka.dto.CityDTO;
import isa.apoteka.service.AddressService;

@RestController
@RequestMapping(value = "/api/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/getAllAddressesForCity")
	//@PreAuthorize("hasRole('PATIENT')")
	public List<Address> loadAll(CityDTO city) {
		return this.addressService.findAllAddressesForCity(city);
	}
}
