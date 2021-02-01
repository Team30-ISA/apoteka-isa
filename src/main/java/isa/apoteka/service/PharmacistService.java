package isa.apoteka.service;

import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.Pharmacist;


public interface PharmacistService {

	void firePharm(Long pharmId);
	Pharmacist hire(Pharmacist pharmacist);
	Pharmacist findByUsername(String username);
	Pharmacy getPharmPharmacy(Long pharmacistId);
	Pharmacist findById(Long id);
	void update(String firstName, String lastName, Long id);
}
