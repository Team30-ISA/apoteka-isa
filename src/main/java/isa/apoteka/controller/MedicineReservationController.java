package isa.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.dto.ReservedMedicineDTO;
import isa.apoteka.service.MedicineReservationService;

@RestController
@RequestMapping(value = "api/medicineReservation")
public class MedicineReservationController {
	
	@Autowired
	private MedicineReservationService medicineReservationService;
	
	@GetMapping("/findReservation")
	@PreAuthorize("hasRole('PHARM')")
	public ReservedMedicineDTO findReservation(String uid) {
		Pharmacist pharmacist = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
		return medicineReservationService.findReservationByPharmacy(uid, pharmacist.getPharmacy().getId());
		} catch (Exception e) {
			return null;
		}
	}

}
