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
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.MedicineDisplay;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.DermatologistDTO;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.service.PharmacyService;


@RestController
@RequestMapping(value = "api/pharmacy")
public class PharmacyController {
	
	@Autowired
	private PharmacyService pharmacyService;
	
	@GetMapping(value = "/findAll")
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
	
	@GetMapping(value = "/findAllDermsNotInPharmacy")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<DermatologistDTO>> findAllDermsNotWorkingInPharmacy(@RequestParam Long id) {

		List<Dermatologist> derms = pharmacyService.findAllDermsNotWorkingInPharmacy(id);

		List<DermatologistDTO> dermDTO = new ArrayList<>();
		for (Dermatologist d : derms) {
			dermDTO.add(new DermatologistDTO(d));
		}

		return new ResponseEntity<>(dermDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/searchDermsInPharmacy")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<DermatologistDTO>> searchDermsWorkingInPharmacy(@RequestParam Long id, String firstName, String lastName) {

		List<Dermatologist> derms = pharmacyService.searchDermsWorkingInPharmacy(id, firstName, lastName);
		System.out.println("************");
		System.out.println(firstName);
		System.out.println(lastName);
		List<DermatologistDTO> dermDTO = new ArrayList<>();
		for (Dermatologist d : derms) {
			dermDTO.add(new DermatologistDTO(d));
		}

		return new ResponseEntity<>(dermDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/searchPharmsInPharmacy")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<PharmacistDTO>> searchPharmsWorkingInPharmacy(@RequestParam Long id, String firstName, String lastName) {

		List<Pharmacist> pharms = pharmacyService.searchPharmsWorkingInPharmacy(id, firstName, lastName);
		List<PharmacistDTO> pharmDTO = new ArrayList<>();
		for (Pharmacist p : pharms) {
			pharmDTO.add(new PharmacistDTO(p));
		}

		return new ResponseEntity<>(pharmDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/searchMedicineInPharmacy")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<Medicine> searchMedicineInPharmacy(Long id, String name) {

		Medicine meds = pharmacyService.searchMedicineInPharmacy(id, name);
		/*List<MedicineDTO> medsDTO = new ArrayList<>();
		for (Medicine m : meds) {
			medsDTO.add(new MedicineDTO(m));
		}*/

		return new ResponseEntity<>(meds, HttpStatus.OK);
	}
	
	@GetMapping(value = "/updateMedicineInPharmacy")
	@PreAuthorize("hasRole('PATIENT')")
	public void updateMedicineInPharmacy(Long pharmId, Long medId, int quantity) {

		pharmacyService.updateMedicineInPharmacy(pharmId, medId, quantity);
		/*List<MedicineDTO> medsDTO = new ArrayList<>();
		for (Medicine m : meds) {
			medsDTO.add(new MedicineDTO(m));
		}*/
	}
}
