package isa.apoteka.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import isa.apoteka.domain.Examination;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.service.ExaminationService;
import isa.apoteka.service.PatientService;
import isa.apoteka.service.PharmacistHolidayService;
import isa.apoteka.service.PharmacistService;
import isa.apoteka.service.PharmacistWorkCalendarService;

@RestController
@RequestMapping(value = "api/examination")
public class ExaminationController {

	@Autowired
	private ExaminationService examintaionService;
	
	@Autowired
	private PharmacistWorkCalendarService pwcService;
	
	@Autowired
	private PharmacistService pharmacistService;
	
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
	public ResponseEntity<Integer> scheduleExamination(@RequestBody Map<String, Object> params) throws Exception {
		Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Date start = new Date(Long.parseLong(params.get("start").toString()));
		Integer duration = Integer.parseInt(params.get("duration").toString());
		Long currExaminationId = Long.parseLong(params.get("currExaminationId").toString());
		if(start == null || duration == null || currExaminationId == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Patient patient;
		Long pharmacistId;
		try{
			patient = examintaionService.findOne(currExaminationId).getPatient();
			pharmacistId = pharm.getId();
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		if(!isPatientFree(patient.getId(), start, duration)) {
			return new ResponseEntity<>(-1, HttpStatus.OK);
		}
		Long pwcId = getCurrPharmWP(start, pharmacistId);
		if(pwcId == null) {
			return new ResponseEntity<>(-2, HttpStatus.OK);
		}
		try {
			examintaionService.createExamination(start, duration, patient, pwcId, pharmacistId);
		} catch (Exception e) {
			return new ResponseEntity<>(-4, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(1, HttpStatus.OK);
		
	}
	
	@GetMapping("/scheduleExaminationPatient")
	//@PostMapping("/scheduleExaminationPatient")
	@PreAuthorize("hasRole('PATIENT')")
	public int scheduleExaminationPatient(String date, int duration, Long pharmacistId) throws Exception {
		System.out.println("GGGGGGG" + pharmacistId);
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String[] s = date.split("-", 3);
		String[] st = s[2].split("T", 2);
		String[] time = st[1].split(":", 2);
		String s2 = s[0] + "/" + s[1] + "/" + st[0] + " " + st[1];
		System.out.println("JJJJJJJJJJ" + s2);
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date start = null;
		Long patientId;
		try {
			start = formatter.parse(s2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Long currExaminationId = Long.parseLong(params.get("currExaminationId").toString());
		if(start == null) {
			return 2;
		}
		try{
			patientId = patient.getId();
		}
		catch (Exception e) {
			return 3;
		}
		if(!isPatientFree(patientId, start, duration)) {
			return 4;
		}
		Long pwcId = getCurrPharmWP(start, pharmacistId);
		if(pwcId == null) {
			return 5;
		}
		/*if(pharmacistHolidayService.isPharmOnHolidays(pharmacistId, start)) {
			return new ResponseEntity<>(-3, HttpStatus.OK);
		}*/
		/*if(examintaionService.createExamination(start, duration, patientId, pwcId, pharmacistId)) {
			return 6;
		}*/
		examintaionService.createExamination(start, duration, patient, pwcId, pharmacistId);
		
		return 1;
		
	}
	
	@GetMapping("/findAllPharmsByDate")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<List<Pharmacy>> findAllPharmsByDate(String date) {
		System.out.println("aaaaaaaaaaaaaaa");
		String[] s = date.split("-", 3);
		String[] st = s[2].split("T", 2);
		String[] time = st[1].split(":", 2);
		String s2 = st[0] + "/" + s[1] + "/" + s[0] + " " + st[1];
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date newDate = null;
		try {
			newDate = formatter.parse(s2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DATE " + newDate);
		return new ResponseEntity<>(examintaionService.getAvailablePharmacies(newDate), HttpStatus.OK);
	}
	
	@GetMapping("/getExaminationsForPatient")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Examination> getExaminationsForPatient(){
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long patientId = patient.getId();
		Date now = new Date();
		List<Examination> ret = examintaionService.getExaminationsForPatient(patientId, now);
		return ret;
	}
	
	@GetMapping("/cancelAppointment")
	//@PostMapping("/cancelAppointment")
	@PreAuthorize("hasRole('PATIENT')")
	public boolean cancelAppointment(Long examId) {
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Patient patient = patientService.findById(patId);
		if(p == null)
			return false;
		Date now = new Date();
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		Examination examination = examintaionService.findOne(examId);
		Date cdate = examination.getStartDate();
		if(examination.getStartDate().getTime() < (new Date()).getTime()) {
			return false;
		}
		Calendar examDate = new GregorianCalendar();
		examDate.setTime(examination.getStartDate());
		if(examination.getPatient() == null)
			return false;
		if(!examination.getPatient().getId().equals(p.getId()))
			return false;
		if(checkIfDayEarlier(examDate, cal)) {
			return false;
		}
		System.out.println(sdf.format(cal.getTime()));
		System.out.println(sdf.format(examDate.getTime()));
		examintaionService.cancelAppointment(examination.getId());
		return true;
	}
	
	@GetMapping("/getAvailablePharmacistsByPharmIdAndDate")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Pharmacist> getAvailablePharmacistsByPharmIdAndDate(Long pharmId, String date){
		System.out.println("aaaaaaaaaaaaaaa");
		String[] s = date.split("-", 3);
		String[] st = s[2].split("T", 2);
		String[] time = st[1].split(":", 2);
		String s2 = st[0] + "/" + s[1] + "/" + s[0] + " " + st[1];
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date newDate = null;
		try {
			newDate = formatter.parse(s2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DATE " + newDate);
		return examintaionService.getAvailablePharmacistsByPharmIdAndDate(pharmId, newDate);
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
			System.out.println(start);
			PeriodDTO pwc = pwcService.findPharmWorkCalendarByPharmIdAndDate(pharmId, start);
			System.out.println(pwc);
			return pwc.getId();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	boolean checkIfDayEarlier(Calendar cc, Calendar c) {
		if(cc.get(Calendar.YEAR) != c.get(Calendar.YEAR))
			return false;
		else if(cc.get(Calendar.MONTH) != c.get(Calendar.MONTH))
			return false;
		else if(cc.get(Calendar.DAY_OF_MONTH) != c.get(Calendar.DAY_OF_MONTH))
			return false;
		else if(cc.get(Calendar.HOUR_OF_DAY) -1 < c.get(Calendar.HOUR_OF_DAY))
			return true;
			
		return true;
	}
	
	
	
}
