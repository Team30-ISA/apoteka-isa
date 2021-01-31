package isa.apoteka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.apoteka.domain.Address;
import isa.apoteka.domain.Authority;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.NewPharmacistDTO;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.CityService;
import isa.apoteka.service.PharmacistService;


@RestController
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {
	
	
	private PharmacistService pharmacistService;
	private AddressService addressService;
	private CityService cityService;
	private PasswordEncoder passwordEncoder;
	private AuthorityService authService;
	
	@Autowired
	public PharmacistController(PharmacistService pharmacistService, AddressService addressService, CityService cityService, PasswordEncoder passwordEncoder, AuthorityService authService) {
		this.pharmacistService = pharmacistService;
		this.addressService = addressService;
		this.cityService = cityService;
		this.passwordEncoder = passwordEncoder;
		this.authService = authService;
	}

	
	@GetMapping("/getLoggedUser")
	@PreAuthorize("hasRole('PHARM')")
	public Pharmacist getLoggedUser() {
		return (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@DeleteMapping(value= "/fire/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> fire(@PathVariable(value = "id") Long pharmId) {
		pharmacistService.firePharm(pharmId);
		Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return response;
		
	}
	
	
	@Transactional(readOnly = false)
	@PostMapping(value= "/save", consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public Pharmacist save(@RequestBody @Valid NewPharmacistDTO newPharmacistDTO) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		City city = cityService.findCityById(newPharmacistDTO.getCityId());
		List<Authority> auth = authService.findByname("ROLE_PHARM");
		Address address = new Address();
		address.setStreet(newPharmacistDTO.getAddress());
		address.setCity(city);
		Address newAddress = addressService.insertNewAddress(address);
		Pharmacist pharmacist = new Pharmacist();
		pharmacist.setUsername(newPharmacistDTO.getUsername());
		pharmacist.setFirstName(newPharmacistDTO.getFirstName());
		pharmacist.setLastName(newPharmacistDTO.getLastName());
		pharmacist.setEmail(newPharmacistDTO.getEmail());
		pharmacist.setPassword(passwordEncoder.encode(newPharmacistDTO.getUsername()));
		pharmacist.setAuthorities(auth);
		pharmacist.setAddress(newAddress);
		pharmacist.setPharmacy(admin.getPharmacy());
		return pharmacistService.hire(pharmacist);

		
	}
	
}
