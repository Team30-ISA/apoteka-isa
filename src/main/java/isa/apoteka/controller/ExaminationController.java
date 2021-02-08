package isa.apoteka.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.Nullable;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.service.ExaminationService;
import isa.apoteka.service.PatientService;
import isa.apoteka.service.PharmacistWorkCalendarService;

@RestController
@RequestMapping(value = "api/examination")
public class ExaminationController {

	@Autowired
	private ExaminationService examintaionService;
	
	@Autowired
	private PharmacistWorkCalendarService pwcService;
	
	@Autowired
	private PatientService patientService;
	
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
	
	@GetMapping("/getNearestExamination")
	@PreAuthorize("hasRole('PHARM')")
	public ExaminationDTO getNearestExamination(Long start, boolean finished) {
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return examintaionService.getNearestExamintaionDTO(pharm.getId(), new Date(start), finished);
	}
	
	@Nullable
	@GetMapping("/countAllTerms")
	@PreAuthorize("hasRole('PHARM')")
	public ResponseEntity<List<Long>> countAllTerms(Long start, int num){
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long pharmacistId = pharm.getId();
		return new ResponseEntity<>(examintaionService.countTermsByDays(pharmacistId, new Date(start), num), HttpStatus.OK);
	}
	
	@GetMapping("/getPatientForNearestExamination")
	@PreAuthorize("hasRole('PHARM')")
	public Long getPatientForNearestExamination(Long start, boolean finished) {
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id;
		try {
			id = examintaionService.getNearestExamintaion(pharm.getId(), new Date(start), finished).getPatient().getId();
		}
		catch (Exception e) {
			return null;
		}
		return id;
	}
	
	@PostMapping("/setReport")
	@PreAuthorize("hasRole('PHARM')")
	public void setReport(@RequestBody Map<String, Object> params, HttpServletResponse response) {
		String report = params.get("report").toString();
		if(report == null || report.equals("")) {
			response.setStatus(400);
			return;
		}
		examintaionService.updateReport(report, Long.parseLong(params.get("examinationId").toString()));
	}
	
	@PostMapping("/scheduleExamination")
	@PreAuthorize("hasRole('PHARM')")
	public ResponseEntity<Integer> scheduleExamination(@RequestBody Map<String, Object> params) {
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Date start = new Date(Long.parseLong(params.get("start").toString()));
		Integer duration = Integer.parseInt(params.get("duration").toString());
		Long currExaminationId = Long.parseLong(params.get("currExaminationId").toString());
		if(start == null || duration == null || currExaminationId == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Long patientId;
		Long pharmacistId;
		try{
			patientId = examintaionService.findOne(currExaminationId).getPatient().getId();
			pharmacistId = pharm.getId();
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		if(!isPatientFree(patientId, start, duration)) {
			return new ResponseEntity<>(-1, HttpStatus.OK);
		}
		Long pwcId = getCurrPharmWP(start, pharmacistId);
		if(pwcId == null) {
			return new ResponseEntity<>(-2, HttpStatus.OK);
		}
		if(!examintaionService.createExamination(start, duration, patientId, pwcId, pharmacistId)) {
			return new ResponseEntity<>(-3, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(1, HttpStatus.OK);
		
	}
	
	public Boolean isPatientFree(Long patientId, Date start, int duration) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.MINUTE, duration);
		Date end = calendar.getTime();
		if(patientService.hasCounselings(patientId, start, end) == true || patientService.hasExaminations(patientId, start, end) == true) {
			return false;
		}
		return true;
	}
	
	public Long getCurrPharmWP(Date start, Long pharmId) {
		try {
			PeriodDTO pwc = pwcService.findPharmWorkCalendarByPharmIdAndDate(pharmId, start);
			return pwc.getId();
		}
		catch (Exception e) {
			return null;
		}
	}
	
}
