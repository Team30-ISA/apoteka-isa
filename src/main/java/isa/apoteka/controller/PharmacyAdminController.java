package isa.apoteka.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.PharmacyAdmin;

@RestController
@RequestMapping(value = "api/pharmacyAdmin")
public class PharmacyAdminController {

	
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
}
