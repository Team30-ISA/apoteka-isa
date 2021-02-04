package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PatientUpdateForm;

public interface PatientService {
	String getLogged();
	Patient findById(Long id);
    Patient findByUsername(String username);
    List<Patient> findAll ();
	void update(PatientUpdateForm puf);
	void updatePassword(PatientUpdateForm puf);
	List<Patient> findAllPatients();
	List<Patient> findAllPatientsNotification(Long id);
	Boolean hasCounselings(Long patientId, Date start, Date end);
	Boolean hasExaminations(Long patientId, Date start, Date end);
}
