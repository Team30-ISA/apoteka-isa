package isa.apoteka.service;

import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.FilteredDTO;
import isa.apoteka.dto.PatientDTO;
import isa.apoteka.dto.SearchFilterDTO;

import java.util.List;

import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacist;


public interface PharmacistService {

	void firePharm(Long pharmId);
	Pharmacist hire(Pharmacist pharmacist);
	Pharmacist findByUsername(String username);
	Pharmacy getPharmPharmacy(Long pharmacistId);
	Pharmacist findById(Long id);
	void update(String firstName, String lastName, Long id);
	List<PatientDTO> findAllExaminedPatients(Long pharmacistId);
	List<FilteredDTO> searchPharms(SearchFilterDTO pharms);
	void save(Pharmacist pharmacist);
}
