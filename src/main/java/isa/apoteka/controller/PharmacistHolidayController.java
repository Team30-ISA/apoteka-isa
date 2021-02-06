package isa.apoteka.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.service.ExaminationService;
import isa.apoteka.service.PharmacistHolidayService;

@RestController
@RequestMapping(value = "api/pharmHoliday")
public class PharmacistHolidayController {

	@Autowired
	private PharmacistHolidayService pharmacistHolidayService;
	@Autowired
	private ExaminationService examinationService;
	
	@GetMapping("/save")
	@PreAuthorize("hasRole('PHARM')")
	public void save(Long start, Long end, HttpServletResponse response) {
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!examinationService.isPharmFree(pharm.getId(), new Date(start), new Date(end))) {
			response.setStatus(400);
			return;
		}
		pharmacistHolidayService.save(pharm, new Date(start), new Date(end));
	}
}
