package isa.apoteka.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.PharmHolidayDTO;
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
	
	@GetMapping("/findAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<PharmHolidayDTO>>  findAll() {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<PharmHolidayDTO> dto = pharmacistHolidayService.findAll(admin.getPharmacy().getId());
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/reject")
	@PreAuthorize("hasRole('ADMIN')")
	public Boolean  reject(Long holidayId) {
		pharmacistHolidayService.reject(holidayId);
		return true;
	}
	
	@GetMapping("/approve")
	@PreAuthorize("hasRole('ADMIN')")
	public Boolean  approve(Long holidayId, Long pharmId, Long start, Long end, HttpServletResponse response) {
		if(!examinationService.isPharmFree(pharmId, new Date(start), new Date(end))) {
			response.setStatus(400);
			return false;
		}
		
		pharmacistHolidayService.approve(holidayId, pharmId, new Date(start), new Date(end));
		return true;
	}
}
