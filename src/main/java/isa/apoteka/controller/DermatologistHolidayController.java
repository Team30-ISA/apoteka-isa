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

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.domain.SystemAdmin;
import isa.apoteka.dto.DermHolidayDTO;
import isa.apoteka.dto.PharmHolidayDTO;
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
	
	@GetMapping("/findAll")
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public ResponseEntity<List<DermHolidayDTO>>  findAll() {
		List<DermHolidayDTO> dto = dermatologistHolidayService.findAll();
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("/reject")
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public Boolean  reject(Long holidayId) {
		dermatologistHolidayService.reject(holidayId);
		return true;
	}
	
	@GetMapping("/approve")
	@PreAuthorize("hasRole('SYS_ADMIN')")
	public Boolean  approve(Long holidayId, Long dermId, Long start, Long end, HttpServletResponse response) {
		if(!counselingService.isDermFree(dermId, new Date(start), new Date(end))) {
			System.out.println("******************************");
			response.setStatus(400);
			return false;   
		}   
		
		dermatologistHolidayService.approve(holidayId, dermId, new Date(start), new Date(end));
		return true;
	}
	
}
