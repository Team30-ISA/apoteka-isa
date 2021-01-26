package isa.apoteka.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PatientUpdateForm;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.User;
import isa.apoteka.dto.PatientDTO;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.dto.UserDTO;
import isa.apoteka.service.PatientService;
import isa.apoteka.service.UserService;
import isa.apoteka.service.impl.CustomUserDetailsService;


// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

	@Autowired
	private PatientService patientService;

	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/patient/{userId}")
	//@PreAuthorize("hasRole('PATIENT')")
	public User loadById(@PathVariable Long userId) {
		return this.patientService.findById(userId);
	}

	@GetMapping("/patient/all")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Patient> loadAll() {
		return this.patientService.findAll();
	}

	@GetMapping("/patient/whoami")
	@PreAuthorize("hasRole('PATIENT')")
	public Patient user(Principal user) {
		return this.patientService.findByUsername(user.getName());
	}
	
	@GetMapping(value = "/patient/findAllPatients")
	public ResponseEntity<List<PatientDTO>> getAllPatients() {

		List<Patient> patients = patientService.findAllPatients();

		List<PatientDTO> patientDTO = new ArrayList<>();
		for (Patient p : patients) {
			patientDTO.add(new PatientDTO(p));
		}

		return new ResponseEntity<>(patientDTO, HttpStatus.OK);
	}
	
	@GetMapping("/patient/getLoggedUser")
	@PreAuthorize("hasRole('PATIENT')")
	public Patient getLoggedUser() {
		return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@GetMapping("/patient/updatePatient")
	@PreAuthorize("hasRole('PATIENT')")
	public String updatePatient(PatientUpdateForm puf) {
		this.patientService.update(puf);
		return puf.getName();
	}
	
	@GetMapping("/patient/updatePassword")
	@PreAuthorize("hasRole('PATIENT')")
	public String updatePassword(PatientUpdateForm puf) {
		this.patientService.updatePassword(puf);
		return puf.getNewpass();
	}
	

}