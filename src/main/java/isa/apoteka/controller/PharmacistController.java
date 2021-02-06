package isa.apoteka.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.server.ResponseStatusException;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.Authority;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.domain.User;
import isa.apoteka.dto.ChangeDataDTO;
import isa.apoteka.dto.FilteredDTO;
import isa.apoteka.dto.NewPharmacistDTO;
import isa.apoteka.dto.PatientDTO;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.dto.SearchFilterDTO;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.CityService;
import isa.apoteka.service.PharmacistService;
import isa.apoteka.service.UserService;


@RestController
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {
	
	private PharmacistService pharmacistService;
	private AddressService addressService;
	private CityService cityService;
	private PasswordEncoder passwordEncoder;
	private AuthorityService authService;
	private UserService userService;
	
	@Autowired
	public PharmacistController(PharmacistService pharmacistService, AddressService addressService, CityService cityService, PasswordEncoder passwordEncoder, AuthorityService authService, UserService userService) {
		this.pharmacistService = pharmacistService;
		this.addressService = addressService;
		this.cityService = cityService;
		this.passwordEncoder = passwordEncoder;
		this.authService = authService;
		this.userService = userService;
	}

	
	@GetMapping("/getLoggedUser")
	@PreAuthorize("hasRole('PHARM')")
	public PharmacistDTO getLoggedUser() {
		Pharmacist pharmacist = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new PharmacistDTO(pharmacist);
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
	public ResponseEntity<?> save(@RequestBody @Valid NewPharmacistDTO newPharmacistDTO) {
		
		User existUser = userService.findByUsername(newPharmacistDTO.getUsername());
		if (existUser != null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}
		
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
		pharmacist.setGender(newPharmacistDTO.getGender());
		try {
			pharmacistService.hire(pharmacist);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
	}
	

	@PostMapping(value= "/update", consumes = "application/json")
	@PreAuthorize("hasRole('PHARM')")
	public ResponseEntity<ChangeDataDTO> update(@RequestBody @Valid ChangeDataDTO changeDataDTO) {
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pharmacistService.update(changeDataDTO.getFirstName(), changeDataDTO.getLastName(), pharm.getId());
		addressService.update(changeDataDTO.getStreet(), changeDataDTO.getCityId(), pharm.getAddress().getId());
		return new ResponseEntity<>(changeDataDTO, HttpStatus.CREATED);
	}
		
	@GetMapping("/getPharmacy")
	@PreAuthorize("hasRole('PHARM')")
	public PharmacyDTO getPharmacy() {
		Pharmacist pharmacist = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new PharmacyDTO(pharmacistService.getPharmPharmacy(pharmacist.getId()));
	}

	@GetMapping("/pharm")
	@PreAuthorize("hasRole('PHARM') || hasRole('ADMIN')")
	public User loadById(Long pharmId) {
		return this.pharmacistService.findById(pharmId);
	}
	
	@PostMapping(value = "/searchPharms")
	@PreAuthorize("hasRole('ADMIN') || hasRole('PATIENT')")
	public ResponseEntity<List<FilteredDTO>> searchPharmsWorkingInPharmacy(@RequestBody SearchFilterDTO searchPharm) {
		List<FilteredDTO> pharms = pharmacistService.searchPharms(searchPharm);
		
		for(FilteredDTO d : pharms){
			System.out.println(d.getPharmacyNames().size());
		}
		return new ResponseEntity<>(pharms, HttpStatus.OK);
	}
	
	@GetMapping("/findAllExaminedPatients")
	@PreAuthorize("hasRole('PHARM')")
	public List<PatientDTO> findAllExaminedPatients() {
		Pharmacist pharmacist = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.pharmacistService.findAllExaminedPatients(pharmacist.getId());
	}
}
