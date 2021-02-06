package isa.apoteka.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.service.CounselingService;
import isa.apoteka.service.DermatologistHolidayService;

@RestController
@RequestMapping(value = "api/dermHoliday")
public class DermatologistHolidayController {
	@Autowired
	private DermatologistHolidayService dermatologistHolidayService;
	@Autowired
	private CounselingService counselingService;
	
	@GetMapping("/save")
	@PreAuthorize("hasRole('DERM')")
	public void save(Long start, Long end, HttpServletResponse response) {
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!counselingService.isDermFree(derm.getId(), new Date(start), new Date(end))) {
			response.setStatus(400);
			return;
		}
		dermatologistHolidayService.save(derm, new Date(start), new Date(end));
	}
	
}
