package isa.apoteka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.dto.ChangeDataDTO;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.PharmacistService;


@RestController
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {
	
	@Autowired
	private PharmacistService pharmacistService;
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/getLoggedUser")
	@PreAuthorize("hasRole('PHARM')")
	public Pharmacist getLoggedUser() {
		return (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@PostMapping(value= "/save", consumes = "application/json")
	@PreAuthorize("hasRole('PHARM')")
	public ResponseEntity<ChangeDataDTO> save(@RequestBody @Valid ChangeDataDTO changeDataDTO) {
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pharmacistService.update(changeDataDTO.getFirstName(), changeDataDTO.getLastName(), pharm.getId());
		addressService.update(changeDataDTO.getStreet(), changeDataDTO.getCityId(), pharm.getAddress().getId());
		return new ResponseEntity<>(changeDataDTO, HttpStatus.CREATED);
		
	}
}
