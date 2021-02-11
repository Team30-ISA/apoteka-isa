package isa.apoteka.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.service.CounselingService;
import isa.apoteka.service.DermatologistWorkCalendarService;
import isa.apoteka.service.PatientService;

@RestController
@RequestMapping(value = "api/counseling")
public class CounselingController {
	
	@Autowired
	private CounselingService counselingService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DermatologistWorkCalendarService dWCService;
	@Autowired
	private EmailService emailService;
	
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
	@GetMapping("/findAllTerms")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<ExaminationDTO>> findAllTerms(Long dermatologistId, Long start) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long pharmacyId = admin.getPharmacy().getId();
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
	@GetMapping("/countAllTerms")
	@PreAuthorize("hasRole('DERM')")
	public ResponseEntity<List<Long>> countAllTerms(Long start, int num){
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long dermatologistId = derm.getId();
		return new ResponseEntity<>(counselingService.countAllTermsByDays(dermatologistId, new Date(start), num), HttpStatus.OK);
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
	public ResponseEntity<Integer> setPatient(@RequestBody Map<String, Object> params) {
		Patient patient = counselingService.getPatientInCounseling(Long.parseLong(params.get("currCounselingId").toString()));
		if(patient == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		Counseling counseling = counselingService.findOne(Long.parseLong(params.get("newCounselingId").toString()));
		if(counseling.getStartDate().getTime() < (new Date()).getTime()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		try {
			if(counselingService.update(patient, Long.parseLong(params.get("newCounselingId").toString()))) {
				emailService.sendCounselingReservation(counseling, patient);
				return new ResponseEntity<>(1, HttpStatus.OK);
			}
			else
				return new ResponseEntity<>(-1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(-1, HttpStatus.OK);
		}
	}
	@Nullable
	@PostMapping("/makeAppointment")
	@PreAuthorize("hasRole('PATIENT')")
	public boolean makeAppointment(Long counsId) {
		System.out.println("AAAAAAAAAAAAAAAAA");
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Patient patient = patientService.findById(patId);
		if(p == null)
			return false;
		Counseling counseling = counselingService.findOne(counsId);
		if(counseling.getStartDate().getTime() < (new Date()).getTime()) {
			return false;
		}
		try {
		counselingService.update(p, counseling.getId());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@GetMapping("/makeAnAppointment")
	@PostMapping("/makeAnAppointment")
	@PreAuthorize("hasRole('PATIENT')")
	public boolean makeAnAppointment(Long counsId) {
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Patient patient = patientService.findById(patId);
		if(p == null)
			return false;
		Counseling counseling = counselingService.findOne(counsId);
		if(counseling.getStartDate().getTime() < (new Date()).getTime()) {
			return false;
		}
		counselingService.sendCounselingReservation(counseling);
		counselingService.makeAppointment(p.getId(), counseling.getId());
		return true;
	}
	
	@GetMapping("/cancelAppointment")
	@PostMapping("/cancelAppointment")
	@PreAuthorize("hasRole('PATIENT')")
	public boolean cancelAppointment(Long counsId) {
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//Patient patient = patientService.findById(patId);
		if(p == null)
			return false;
		Date now = new Date();
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		Counseling counseling = counselingService.findOne(counsId);
		Date cdate = counseling.getStartDate();
		if(counseling.getStartDate().getTime() < (new Date()).getTime()) {
			return false;
		}
		Calendar counsDate = new GregorianCalendar();
		counsDate.setTime(counseling.getStartDate());
		if(counseling.getPatient() == null)
			return false;
		if(!counseling.getPatient().getId().equals(p.getId()))
			return false;
		if(checkIfDayEarlier(counsDate, cal)) {
			return false;
		}
		System.out.println(sdf.format(cal.getTime()));
		System.out.println(sdf.format(counsDate.getTime()));
		counselingService.cancelAppointment(counseling.getId());
		return true;
	}
	
	@PostMapping("/setReport")
	@PreAuthorize("hasRole('DERM')")
	public Boolean setReport(@RequestBody Map<String, Object> params) {
		String report = params.get("report").toString();
		try {
			if(!counselingService.updateReport(report, Long.parseLong(params.get("counselingId").toString())))
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
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
	
	@GetMapping("/isDermFree")
	@PreAuthorize("hasRole('DERM')")
	public Boolean isDermFree(Long startDate, Long endDate) {
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return counselingService.isDermFree(derm.getId(), new Date(startDate), new Date(endDate));
	}
	
	@PostMapping("/scheduleCounseling")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Integer> scheduleCounseling(@RequestBody Map<String, Object> params) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Date start = new Date(Long.parseLong(params.get("start").toString()));
		Integer duration = Integer.parseInt(params.get("duration").toString());
		Float price = Float.parseFloat(params.get("price").toString());
		Long dermId = Long.parseLong(params.get("dermId").toString());
		if(start == null || duration == null || price == null || dermId == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Long dwcId = getCurrDermWP(start, dermId, admin.getPharmacy().getId());
		if(dwcId == null) {
			return new ResponseEntity<>(-1, HttpStatus.OK);
		}
		if(!counselingService.createCounseling(start, duration, price, dwcId, dermId, admin.getPharmacy().getId())) {
			return new ResponseEntity<>(-2, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(1, HttpStatus.OK);
		
	}
	
	public Long getCurrDermWP(Date start, Long dermId, Long pharmId) {
		try {
			PeriodDTO dwc = dWCService.findDermWorkCalendarForDermAndDate(pharmId, dermId, start);
			return dwc.getId();
		}
		catch (Exception e) {
			return null;
		}
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
	
	@GetMapping("/findAllCounselingsForPharmacy")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Counseling> findAllCounselingsForPharmacy(Long pharmId){
			List<Counseling> counselings = counselingService.findAllByPharmId(pharmId);
			List<Counseling> ret = new ArrayList<Counseling>();
			for(Counseling s: counselings) {
				if(s.getPatient() == null)
					ret.add(s);
			}
			return ret;
	}
	
	@GetMapping("/findAllByPatientId")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Counseling> findAllByPatientId(Long patId){
			List<Counseling> counselings = counselingService.findAllByPatientId(patId);
			List<Counseling> ret = new ArrayList<Counseling>();
			for(Counseling s: counselings) {
				if(s.getPatient() != null)
					if(s.getPatient().getId().equals(patId))
						ret.add(s);
			}
			return ret;
	}
	
	@GetMapping("/findDermatologistForCounseling")
	@PreAuthorize("hasRole('PATIENT')")
	public Dermatologist findDermatologistForCounseling(Long counsId){
			Dermatologist derm = counselingService.findDermatologistForCounseling(counsId);
			return derm;
	}
	
	@GetMapping("/findOneCounseling")
	@PreAuthorize("hasRole('PATIENT')")
	public Counseling findOne(Long counsId){
			Counseling c = counselingService.findOne(counsId);
			return c;
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
