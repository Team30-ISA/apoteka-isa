package isa.apoteka.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.AddNewMedicineDTO;
import isa.apoteka.service.MedicineInPharmacyService;

@RestController
@RequestMapping(value = "api/medicineInPharmacy")
public class MedicineInPharmacyController {
	
	private MedicineInPharmacyService medInPharmacyService;
	
	@Autowired
	public MedicineInPharmacyController(MedicineInPharmacyService medInPharmacyService) {
		this.medInPharmacyService = medInPharmacyService;
	}

	@PostMapping(value= "/addMedicine", consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<AddNewMedicineDTO> addMedicine(@RequestBody AddNewMedicineDTO addNewMedicineDTO) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		medInPharmacyService.addMedicine(addNewMedicineDTO.getId(), admin.getPharmacy().getId());
		return new ResponseEntity<>(addNewMedicineDTO, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping(value= "/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> fire(@PathVariable(value = "id") Long medId) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		medInPharmacyService.deleteMedication(medId, admin.getPharmacy().getId());
		Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return response;
		
	}
}
