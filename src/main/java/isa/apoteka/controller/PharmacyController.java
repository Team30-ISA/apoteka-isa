package isa.apoteka.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.User;
import isa.apoteka.dto.DermatologistDTO;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.dto.UserDTO;
import isa.apoteka.service.PharmacyService;


@RestController
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@GetMapping
	public ResponseEntity<List<PharmacyDTO>> getAllPharmacies() {

		List<Pharmacy> pharmacies = pharmacyService.findAll();

		// convert students to DTOs
		List<PharmacyDTO> pharmacyDTO = new ArrayList<>();
		for (Pharmacy p : pharmacies) {
			pharmacyDTO.add(new PharmacyDTO(p));
		}

		return new ResponseEntity<>(pharmacyDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/findByName")
	public ResponseEntity<PharmacyDTO> getPharmacyByName(@RequestParam String name) {

		Pharmacy pharmacy = pharmacyService.findByName(name);

		// convert students to DTOs
		PharmacyDTO pharmacyDTO = new PharmacyDTO(pharmacy);
		return new ResponseEntity<>(pharmacyDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findAllDermsInPharmacy")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<DermatologistDTO>> findAllDermsWorkingInPharmacy(@RequestParam Long id) {

		List<Dermatologist> derms = pharmacyService.findAllDermsWorkingInPharmacy(id);

		List<DermatologistDTO> dermDTO = new ArrayList<>();
		for (Dermatologist d : derms) {
			dermDTO.add(new DermatologistDTO(d));
		}

		return new ResponseEntity<>(dermDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findAllPharmsInPharmacy")
	public ResponseEntity<List<PharmacistDTO>> findAllPharmsWorkingInPharmacy(@RequestParam Long id) {

		List<Pharmacist> pharms = pharmacyService.findAllPharmsWorkingInPharmacy(id);

		List<PharmacistDTO> pharmDTO = new ArrayList<>();
		for (Pharmacist p : pharms) {
			pharmDTO.add(new PharmacistDTO(p));
		}

		return new ResponseEntity<>(pharmDTO, HttpStatus.OK);
	}
}
