package isa.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Medicine;
import isa.apoteka.service.MedicineService;

@RestController
@RequestMapping(value = "api/medicine")
public class MedicineContoller {
	
	@Autowired
	private MedicineService medicineService;
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('DERM')")
	public List<Medicine> getAll() {
		return medicineService.findAll();
	}

}
