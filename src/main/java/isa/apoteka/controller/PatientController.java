package isa.apoteka.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import isa.apoteka.dto.ChangeDataDTO;
import isa.apoteka.dto.ComplaintsListsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PatientUpdateForm;
import isa.apoteka.domain.ReservedMedicine;
import isa.apoteka.domain.User;
import isa.apoteka.dto.PatientDTO;

import isa.apoteka.service.MedicineReservationService;
import isa.apoteka.service.MedicineService;
import isa.apoteka.service.AddressService;
import isa.apoteka.service.CounselingService;
import isa.apoteka.service.ExaminationService;

import isa.apoteka.service.PatientService;


// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

	@Autowired
	private PatientService patientService;
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private MedicineReservationService mrService;
	
	private CounselingService counselingService;
	@Autowired
	private ExaminationService examinationService;
	@Autowired
	private AddressService addressService;

	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/patient/{userId}")
	//@PreAuthorize("hasRole('PATIENT')")
	public User loadById(@PathVariable Long userId) {
		return this.patientService.findById(userId);
	}

	@GetMapping("/patient/all")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Patient> loadAll() {
		return this.patientService.findAll();
	}

	@GetMapping("/patient/whoami")
	@PreAuthorize("hasRole('PATIENT')")
	public Patient user(Principal user) {
		return this.patientService.findByUsername(user.getName());
	}
	
	@GetMapping(value = "/patient/findAllPatients")
	public ResponseEntity<List<PatientDTO>> getAllPatients() {

		List<Patient> patients = patientService.findAllPatients();

		List<PatientDTO> patientDTO = new ArrayList<>();
		for (Patient p : patients) {
			patientDTO.add(new PatientDTO(p));
		}

		return new ResponseEntity<>(patientDTO, HttpStatus.OK);
	}
	
	@GetMapping("/patient/getLoggedUser")
	@PreAuthorize("hasRole('PATIENT')")
	public Patient getLoggedUser() {
		return (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@PostMapping(value = "/patient/updatePatient", consumes = "application/json")
	@PreAuthorize("hasRole('PATIENT')")
	public ResponseEntity<PatientUpdateForm> updatePatient(@RequestBody @Valid PatientUpdateForm puf) {
		Patient pat = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		patientService.update(puf);
		addressService.update(puf.getStreet(), puf.getCityId(), pat.getAddress().getId());
		return new ResponseEntity<>(puf, HttpStatus.CREATED);
	}
	
	@GetMapping("/patient/updatePassword")
	@PreAuthorize("hasRole('PATIENT')")
	public String updatePassword(PatientUpdateForm puf) {
		this.patientService.updatePassword(puf);
		return puf.getNewPass();
	}
	
	@GetMapping("/patient/updateReservedMedicine")
	@PreAuthorize("hasRole('PATIENT')")
	public void updateReservedMedicineForPatient(Long patId, Long medId, int quantity, String date) {
		String[] s = date.split("-", 3);
		String s2 = s[2] + "/" + s[1] + "/" + s[0];
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date newDate = null;
		String uid = UUID.randomUUID().toString();
		try {
			newDate = formatter.parse(s2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReservedMedicine rm = new ReservedMedicine();
		rm.setDate(newDate);
		rm.setPatient(patientService.findById(patId));
		rm.setMedicine(medicineService.findOne(medId));
		rm.setQuantity(quantity);
		rm.setUid(uid);
		mrService.SendNotification(rm);
		System.out.println(medId instanceof Long);
		this.patientService.updateReservedMedicineForPatient(patId, medId, quantity, newDate, uid);
	}
	
	@GetMapping("/patient/findAllReservedMedicine")
	@PreAuthorize("hasRole('PATIENT')")
	public List<Medicine> searchReservedMedicineForPatient(Long id) {
		return this.patientService.searchReservedMedicineForPatient(id);
	}

	@GetMapping("/patient/isFree")
	@PreAuthorize("hasRole('DERM') || hasRole('PHARM')")
	public ResponseEntity<Boolean> isFree(Long counselingId, Long startDate, int duration) {
		Date start = new Date(startDate);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.MINUTE, duration);
		Date end = calendar.getTime();
		Patient patient;
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_DERM"))) {
			patient = counselingService.getPatientInCounseling(counselingId);
		}
		else {
			patient = examinationService.getPatientInExamination(counselingId);
		}
		if(patient == null)
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		if(patientService.hasCounselings(patient.getId(), start, end) == false && patientService.hasExaminations(patient.getId(), start, end) == false) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
	

}
