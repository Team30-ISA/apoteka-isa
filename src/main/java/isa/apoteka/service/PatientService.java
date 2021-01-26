package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PatientUpdateForm;
import isa.apoteka.domain.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public interface PatientService {
	String getLogged();
	Patient findById(Long id);
    Patient findByUsername(String username);
    List<Patient> findAll ();
	void update(PatientUpdateForm puf);
	void updatePassword(PatientUpdateForm puf);
	List<Patient> findAllPatients();
}
