package isa.apoteka.controller;


import javax.validation.Valid;

import isa.apoteka.dto.PharmacistDTO;
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

import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.ChangeDataDTO;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.PharmacyAdminService;

@RestController
@RequestMapping(value = "api/pharmacyAdmin")
public class PharmacyAdminController {

	@Autowired
	private PharmacyAdminService pharmacyAdminService;
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/getLoggedUser")
	@PreAuthorize("hasRole('ADMIN')")
	public PharmacyAdmin getLoggedUser() {
		return (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@GetMapping("/getPharmacy")
	@PreAuthorize("hasRole('ADMIN')")
	public Long getPharmacy() {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return admin.getPharmacy().getId();
	}
	
	@PostMapping(value= "/save", consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ChangeDataDTO> save(@RequestBody @Valid ChangeDataDTO changeDataDTO) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pharmacyAdminService.update(changeDataDTO.getFirstName(), changeDataDTO.getLastName(), admin.getId());
		addressService.update(changeDataDTO.getStreet(), changeDataDTO.getCityId(), admin.getAddress().getId());
		return new ResponseEntity<>(changeDataDTO, HttpStatus.CREATED);
	}

	@PostMapping(consumes = "application/json")
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<?> createPharmacyAdmin(@RequestBody @Valid PharmacistDTO pharmacyAdmin) {
		try {
			return new ResponseEntity<>(pharmacyAdminService.create(pharmacyAdmin), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
