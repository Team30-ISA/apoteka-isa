package isa.apoteka.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.domain.User;
import isa.apoteka.dto.HireDermDTO;
import isa.apoteka.service.DermatologistService;

@RestController
@RequestMapping(value = "api/dermatologist")
public class DermatologistController {
	
	@Autowired
	private DermatologistService dermatologistService;
	
	@GetMapping("/getLoggedUser")
	@PreAuthorize("hasRole('DERM')")
	public Dermatologist getLoggedUser() {
		return (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@GetMapping("/derm")
	@PreAuthorize("hasRole('ADMIN')")
	public User loadById(Long dermId) {
		return this.dermatologistService.findById(dermId);
	}
	
	@PostMapping(value= "/hire", consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HireDermDTO> hire(@RequestBody @Valid HireDermDTO hireDermDTO) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		dermatologistService.hireDerm(hireDermDTO.getDermId(), admin.getPharmacy().getId());
		return new ResponseEntity<>(hireDermDTO, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping(value= "/fire/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> fire(@PathVariable(value = "id") Long dermId) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		dermatologistService.fireDerm(dermId, admin.getPharmacy().getId());
		Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return response;
		
	}
}

