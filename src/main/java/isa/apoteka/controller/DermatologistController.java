package isa.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.User;
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
}

