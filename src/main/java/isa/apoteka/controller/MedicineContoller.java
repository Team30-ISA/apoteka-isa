package isa.apoteka.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.DermatologistDTO;
import isa.apoteka.dto.MedicineDTO;
import isa.apoteka.dto.MedicineNameDTO;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.service.CounselingService;
import isa.apoteka.service.ExaminationService;
import isa.apoteka.service.MedicineService;

@RestController
@RequestMapping(value = "api/medicine")
public class MedicineContoller {
	
	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private CounselingService counselingService;
	
	@Autowired
	private ExaminationService examinationService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('DERM') || hasRole('PHARM')")
	public List<Medicine> getAll() {
		return medicineService.findAll();
	}
	
	@GetMapping("/searchMedicinesByName")
	@PreAuthorize("hasRole('DERM')  || hasRole('PHARM')")	
	public List<Medicine> searchMedicinesByName(String name){
		return medicineService.searchMedicinesByName(name);
	}
	
	@GetMapping("/isPatientAllergic")
	@PreAuthorize("hasRole('DERM')  || hasRole('PHARM')")	
	public ResponseEntity<Boolean> isPatientAllergic(Long medicineId, Long counselingId){
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_DERM"))) {
			if(!dermAuthorizationForCounseling(counselingId))
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			Patient patient = counselingService.getPatientInCounseling(counselingId);
			if(patient == null)
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			Long patientId = patient.getId();
			return new ResponseEntity<>(medicineService.isPatientAllergic(patientId, medicineId), HttpStatus.OK);
		}
		else {
			Patient patient = examinationService.getPatientInExamination(counselingId);
			if(patient == null)
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			Long patientId = patient.getId();
			return new ResponseEntity<>(medicineService.isPatientAllergic(patientId, medicineId), HttpStatus.OK);
		}
	}
	
	@GetMapping("/getSubstitutesOfMedicine")
	@PreAuthorize("hasRole('DERM')  || hasRole('PHARM')")
	public List<Medicine> getSubstitutesOfMedicine(Long id) {
		return medicineService.getSubstitutesOfMedicine(id);
	}
	
	@GetMapping("/isMedicineAvailableInPharmacy")
	@PreAuthorize("hasRole('DERM')  || hasRole('PHARM')")
	public ResponseEntity<Boolean> isMedicineAvailableInPharmacy(Long medicineId, Long counselingId) {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_DERM"))) {
			if(!dermAuthorizationForCounseling(counselingId))
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			Pharmacy pharmacy = counselingService.getPharmacyInCounseling(counselingId);
			if(pharmacy == null)
				return null;
			Long pharmacyId = pharmacy.getId();
			return new ResponseEntity<>(medicineService.isMedicineAvailableInPharmacy(medicineId, pharmacyId), HttpStatus.OK);
		}
		else {
			Pharmacy pharmacy = examinationService.getPharmacyInExamination(counselingId);
			if(pharmacy == null)
				return null;
			Long pharmacyId = pharmacy.getId();
			return new ResponseEntity<>(medicineService.isMedicineAvailableInPharmacy(medicineId, pharmacyId), HttpStatus.OK);
		}
		
	}
	
	public Boolean dermAuthorizationForCounseling(Long counselingId) {
		Counseling counseling = counselingService.findOne(counselingId);
		try {
			Dermatologist dermatologist = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(!counseling.getDermatologistWorkCalendar().getDermatologist().getId().equals(dermatologist.getId()))
				return false;
		} catch(Exception e){
			return false;
		}
		return true;
	}
	
	@GetMapping(value = "/findAllMedicineInPharmacy")
	public ResponseEntity<List<MedicineDTO>> findAllMedicineInPharmacy() {
		List<MedicineDTO> med = medicineService.findAllMedicineInPharmacy();
		return new ResponseEntity<>(med, HttpStatus.OK);
	}
	
	@GetMapping(value = "/searchMedicineInPharmacy")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<MedicineDTO>> searchMedicineInPharmacy(@RequestParam String name) {

		List<MedicineDTO> med = medicineService.searchMedicineInPharmacy(name);
		return new ResponseEntity<>(med, HttpStatus.OK);
	}

	
	@GetMapping(value = "/findAllMedicineNotInPharmacy")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<MedicineNameDTO>> findAllMedicineNotInPharmacy() {

		List<MedicineNameDTO> med = medicineService.findAllMedicineNotInPharmacy();

		return new ResponseEntity<>(med, HttpStatus.OK);
	}
	
	@GetMapping(value = "/findAllMedicine")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<MedicineNameDTO>> findAllMedicine() {

		List<MedicineNameDTO> med = medicineService.findAllMedicine();

		return new ResponseEntity<>(med, HttpStatus.OK);
	}
}
