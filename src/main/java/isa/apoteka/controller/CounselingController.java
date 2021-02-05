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

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.service.CounselingService;
import isa.apoteka.service.PatientService;

@RestController
@RequestMapping(value = "api/counseling")
public class CounselingController {
	
	@Autowired
	private CounselingService counselingService;
	@Autowired
	private PatientService patientService;
	
	@Nullable
	@GetMapping("/findAllTermsByDay")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<List<ExaminationDTO>> findAllTermsByDay(Long pharmacyId, Long start) {
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long dermatologistId = derm.getId();
		if(Boolean.FALSE.equals(isDermInPharmacy(dermatologistId, pharmacyId))) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(counselingService.findAllTermsByDay(pharmacyId, dermatologistId, new Date(start)), HttpStatus.OK);
	}
	
	@Nullable
	@GetMapping("/countTerms")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<List<Long>> countTerms(Long pharmacyId, Long start, int num){
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long dermatologistId = derm.getId();
		if(Boolean.FALSE.equals(isDermInPharmacy(dermatologistId, pharmacyId))) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(counselingService.countTermsByDays(pharmacyId, dermatologistId, new Date(start), num), HttpStatus.OK);
	}
	
	@Nullable
	@GetMapping("/countTermsByMonths")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<List<Long>> countTermsByMonths(Long pharmacyId, Long start){
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long dermatologistId = derm.getId();
		if(Boolean.FALSE.equals(isDermInPharmacy(dermatologistId, pharmacyId))) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(counselingService.countTermsByMonths(pharmacyId, dermatologistId, new Date(start)), HttpStatus.OK);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<ExaminationDTO> getById(Long id) {
		if(!dermAuthorizationForCounseling(id))
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<>(counselingService.findOneDTO(id), HttpStatus.OK);
	}
	
	@GetMapping("/getNearestCounseling")
	@PreAuthorize("hasRole('DERM')")
	public ExaminationDTO getNearestCounseling(Long start, boolean finished) {
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return counselingService.getNearestCounselingDTO(derm.getId(), new Date(start), finished);
	}
	
	@GetMapping("/getPatientForNearestCounseling")
	@PreAuthorize("hasRole('DERM')")
	public Long getPatientForNearestCounseling(Long start, boolean finished) {
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id;
		try {
			id = counselingService.getNearestCounseling(derm.getId(), new Date(start), finished).getPatient().getId();
		}
		catch (Exception e) {
			return null;
		}
		return id;
	}
	
	@PostMapping("/setPatient")
	@PreAuthorize("hasRole('DERM')")
	public void setPatient(@RequestBody Map<String, Object> params, HttpServletResponse response) {
		Patient patient = counselingService.getPatientInCounseling(Long.parseLong(params.get("currCounselingId").toString()));
		if(patient == null)
			return;
		Counseling counseling = counselingService.findOne(Long.parseLong(params.get("newCounselingId").toString()));
		if(counseling.getStartDate().getTime() < (new Date()).getTime()) {
			response.setStatus(400);
			return;
		}
		counselingService.update(patient.getId(), Long.parseLong(params.get("newCounselingId").toString()));
	}
	
	@PostMapping("/setReport")
	@PreAuthorize("hasRole('DERM')")
	public void setReport(@RequestBody Map<String, Object> params, HttpServletResponse response) {
		String report = params.get("report").toString();
		if(report == null || report.equals("")) {
			response.setStatus(400);
			return;
		}
		counselingService.updateReport(report, Long.parseLong(params.get("counselingId").toString()));
	}
	
	@PostMapping("/addCounseling")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<String> addCounseling(@RequestBody Map<String, Object> params) {
		Date start = new Date(Long.parseLong(params.get("startDate").toString()));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.MINUTE, Integer.parseInt(params.get("duration").toString()));
		Date end = calendar.getTime();
		Patient patient = counselingService.getPatientInCounseling(Long.parseLong(params.get("currCounselingId").toString()));
		if(patient == null)
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		if(patientService.hasCounselings(patient.getId(), start, end) == false && patientService.hasExaminations(patient.getId(), start, end) == false) {
			return new ResponseEntity<>("Patient is not free!", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	@GetMapping("/getPharmId")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<Long> getPharmId(Long counselingId) {
		Counseling counseling = counselingService.findOne(counselingId);
		if(counseling == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		if(counseling.getDermatologistWorkCalendar() == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		if(counseling.getDermatologistWorkCalendar().getPharmacy() == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(counseling.getDermatologistWorkCalendar().getPharmacy().getId(), HttpStatus.OK);
	}
	
	public List<Pharmacy> findAllPharmaciesByDermatologist(Long dermatologistId) {
		return counselingService.findAllPharmaciesByDermatologist(dermatologistId);
	}
	
	public Boolean isDermInPharmacy(Long dermatologistId, Long pharmacyId) {
		List<Pharmacy> pharms = findAllPharmaciesByDermatologist(dermatologistId);
		if(pharms == null) {
			return false;
		}
		for(Pharmacy p : pharms) {
			if(p.getId().equals(pharmacyId)) {
				return true;
			}
		}
		return false;
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
