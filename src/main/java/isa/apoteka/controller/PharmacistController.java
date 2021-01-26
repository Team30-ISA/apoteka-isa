package isa.apoteka.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.apoteka.domain.Pharmacist;


@RestController
@RequestMapping(value = "api/pharmacist")
public class PharmacistController {

	//@Autowired
	//private PharmacistService pharmacistService;
	
	@GetMapping("/getLoggedUser")
	@PreAuthorize("hasRole('PHARM')")
	public Pharmacist getLoggedUser() {
		return (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}