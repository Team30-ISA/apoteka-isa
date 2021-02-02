package isa.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Boolean isPatientAllergic(Long medicineId, Long counselingId){
		Patient patient = counselingService.getPatientInCounseling(counselingId);
		if(patient == null)
			return null;
		Long patientId = patient.getId();
		return medicineService.isPatientAllergic(patientId, medicineId);
	}
	
	@GetMapping("/getSubstitutesOfMedicine")
	@PreAuthorize("hasRole('DERM')")
	public List<Medicine> getSubstitutesOfMedicine(Long id) {
		return medicineService.getSubstitutesOfMedicine(id);
	}
	
	@GetMapping("/isMedicineAvailableInPharmacy")
	@PreAuthorize("hasRole('DERM')")
	public Boolean isMedicineAvailableInPharmacy(Long medicineId, Long counselingId) {
		Pharmacy pharmacy = counselingService.getPharmacyInCounseling(counselingId);
		if(pharmacy == null)
			return null;
		Long pharmacyId = pharmacy.getId();
		return medicineService.isMedicineAvailableInPharmacy(medicineId, pharmacyId);
		
	}

}
