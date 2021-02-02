package isa.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/save")
	@PreAuthorize("hasRole('DERM')")
	public TherapyDTO save(int duration, Long medicineId, Long counselingId) {
		Patient patient = counselingService.getPatientInCounseling(counselingId);
		if(patient == null)
			return null;
		Medicine medicine = medicineService.findOne(medicineId);
		if(medicine == null)
			return null;
		return new TherapyDTO(therapyService.save(new Therapy(duration, patient, medicine)));
	}
}
