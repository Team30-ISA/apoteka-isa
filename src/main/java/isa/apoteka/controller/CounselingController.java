package isa.apoteka.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.CounselingDTO;
import isa.apoteka.service.CounselingService;

@RestController
@RequestMapping(value = "api/counseling")
public class CounselingController {
	
	@Autowired
	private CounselingService counselingService;
	
	@GetMapping("/findAllTermsByDay")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<List<CounselingDTO>> findAllTermsByDay(Long pharmacyId, Long start) {
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long dermatologistId = derm.getId();
		if(!isDermInPharmacy(dermatologistId, pharmacyId)) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(counselingService.findAllTermsByDay(pharmacyId, dermatologistId, new Date(start)), HttpStatus.OK);
	}
	
	@GetMapping("/countTerms")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<List<Long>> countTerms(Long pharmacyId, Long start, int num){
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long dermatologistId = derm.getId();
		if(!isDermInPharmacy(dermatologistId, pharmacyId)) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(counselingService.countTermsByDays(pharmacyId, dermatologistId, new Date(start), num), HttpStatus.OK);
	}
	
	@GetMapping("/countTermsByMonths")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<List<Long>> countTermsByMonths(Long pharmacyId, Long start){
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long dermatologistId = derm.getId();
		if(!isDermInPharmacy(dermatologistId, pharmacyId)) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(counselingService.countTermsByMonths(pharmacyId, dermatologistId, new Date(start)), HttpStatus.OK);
	}
	
	public List<Pharmacy> findAllPharmaciesByDermatologist(Long dermatologistId) {
		return counselingService.findAllPharmaciesByDermatologist(dermatologistId);
	}
	
	public Boolean isDermInPharmacy(Long dermatologistId, Long pharmacyId) {
		List<Pharmacy> pharms = findAllPharmaciesByDermatologist(dermatologistId);
		if(pharms == null) {
			return false;
		}
		for(Pharmacy p : pharms) {
			if(p.getId() == pharmacyId) {
				return true;
			}
		}
		return false;
	}
}
