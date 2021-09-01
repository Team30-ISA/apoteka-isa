package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PatientUpdateForm;
import isa.apoteka.dto.ComplaintsListsDTO;
import isa.apoteka.dto.PatientDTO;

public interface PatientService {
	String getLogged();
	Patient findById(Long id);
    Patient findByUsername(String username);
    List<Patient> findAll();
    List<PatientDTO> findAllDTO();
	void update(PatientUpdateForm puf);
	void updatePassword(PatientUpdateForm puf);
	List<Patient> findAllPatients();
	List<Patient> findAllPatientsNotification(Long id);
	List<Medicine> searchReservedMedicineForPatient(Long id);
	void updateReservedMedicineForPatient(Long patId, Long medId, int quantity, Date date, String uid);
	Boolean hasCounselings(Long patientId, Date start, Date end);
	Boolean hasExaminations(Long patientId, Date start, Date end);
	List<PatientDTO> findAllByName(String firstName, String lastName);
    ComplaintsListsDTO findAllEntitiesToComplain();
}