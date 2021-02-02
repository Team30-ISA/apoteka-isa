package isa.apoteka.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.Nullable;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.service.ExaminationService;

@RestController
@RequestMapping(value = "api/examination")
public class ExaminationController {

	@Autowired
	private ExaminationService examintaionService;
	
	@Nullable
	@GetMapping("/findAllTermsByDay")
	@PreAuthorize("hasRole('PHARM')")
	public ResponseEntity<List<ExaminationDTO>> findAllTermsByDay(Long start) {
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<>(examintaionService.findAllTermsByDay(pharm.getId(), new Date(start)), HttpStatus.OK);
	}
	
	@Nullable
	@GetMapping("/countTerms")
	@PreAuthorize("hasRole('PHARM')")
	public ResponseEntity<List<Long>> countTerms(Long start, int num){
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<>(examintaionService.countTermsByDays(pharm.getId(), new Date(start), num), HttpStatus.OK);
	}
	
	@Nullable
	@GetMapping("/countTermsByMonths")
	@PreAuthorize("hasRole('PHARM')")
	public ResponseEntity<List<Long>> countTermsByMonths(Long start){
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ResponseEntity<>(examintaionService.countTermsByMonths(pharm.getId(), new Date(start)), HttpStatus.OK);
	}
}
