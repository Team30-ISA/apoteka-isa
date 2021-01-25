package isa.apoteka.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.UserTokenState;
import isa.apoteka.service.DermatologistService;

@RestController
@RequestMapping(value = "api/dermatologist")
public class DermatologistController {
	
	@Autowired
	private DermatologistService dermatologistService;
	
	@GetMapping("/getLoggedUser")
	@PreAuthorize("hasRole('DERM')")
	public Dermatologist getLoggedUser() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		return (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@GetMapping("/isLogged")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<Boolean> isLogged() {
		return ResponseEntity.ok(true);
	}
}

