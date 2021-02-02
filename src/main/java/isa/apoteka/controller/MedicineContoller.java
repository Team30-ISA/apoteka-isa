package isa.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.service.CounselingService;
import isa.apoteka.service.MedicineService;

@RestController
@RequestMapping(value = "api/medicine")
public class MedicineContoller {
	
	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private CounselingService counselingService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('DERM')")
	public List<Medicine> getAll() {
		return medicineService.findAll();
	}
	
	@GetMapping("/searchMedicinesByName")
	@PreAuthorize("hasRole('DERM')")	
	public List<Medicine> searchMedicinesByName(String name){
		return medicineService.searchMedicinesByName(name);
	}
	
	@GetMapping("/isPatientAllergic")
	@PreAuthorize("hasRole('DERM')")	
	public ResponseEntity<Boolean> isPatientAllergic(Long medicineId, Long counselingId){
		if(!dermAuthorizationForCounseling(counselingId))
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		Patient patient = counselingService.getPatientInCounseling(counselingId);
		if(patient == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		Long patientId = patient.getId();
		return new ResponseEntity<>(medicineService.isPatientAllergic(patientId, medicineId), HttpStatus.OK);
	}
	
	@GetMapping("/getSubstitutesOfMedicine")
	@PreAuthorize("hasRole('DERM')")
	public List<Medicine> getSubstitutesOfMedicine(Long id) {
		return medicineService.getSubstitutesOfMedicine(id);
	}
	
	@GetMapping("/isMedicineAvailableInPharmacy")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<Boolean> isMedicineAvailableInPharmacy(Long medicineId, Long counselingId) {
		if(!dermAuthorizationForCounseling(counselingId))
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		Pharmacy pharmacy = counselingService.getPharmacyInCounseling(counselingId);
		if(pharmacy == null)
			return null;
		Long pharmacyId = pharmacy.getId();
		return new ResponseEntity<>(medicineService.isMedicineAvailableInPharmacy(medicineId, pharmacyId), HttpStatus.OK);
		
	}
	
	public Boolean dermAuthorizationForCounseling(Long counselingId) {
		Counseling counseling = counselingService.findOne(counselingId);
		try {
			Dermatologist dermatologist = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(counseling.getDermatologistWorkCalendar().getDermatologist().getId() != dermatologist.getId())
				return false;
		} catch(Exception e){
			return false;
		}
		return true;
	}

}
