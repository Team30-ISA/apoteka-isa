package isa.apoteka.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Therapy;
import isa.apoteka.dto.TherapyDTO;
import isa.apoteka.service.CounselingService;
import isa.apoteka.service.MedicineService;
import isa.apoteka.service.TherapyService;

@RestController
@RequestMapping(value = "api/therapy")
public class TherapyController {

	@Autowired
	private TherapyService therapyService;
	@Autowired
	private CounselingService counselingService;
	@Autowired
	private MedicineService medicineService;
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<TherapyDTO> save(@RequestBody Map<String, Object> params) {
		if(!dermAuthorizationForCounseling(Long.parseLong(params.get("counselingId").toString())))
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
		Patient patient = counselingService.getPatientInCounseling(Long.parseLong(params.get("counselingId").toString()));
		if(patient == null)
			return null;
		Medicine medicine = medicineService.findOne(Long.parseLong(params.get("medicineId").toString()));
		if(medicine == null)
			return null;
		return new ResponseEntity<>(new TherapyDTO(therapyService.save(new Therapy(Integer.parseInt(params.get("duration").toString()), patient, medicine))), HttpStatus.OK);
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
}
