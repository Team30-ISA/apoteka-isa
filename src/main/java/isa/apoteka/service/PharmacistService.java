package isa.apoteka.service;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;

public interface PharmacistService {
	Pharmacy getPharmPharmacy(Long pharmacistId);
	Pharmacist findById(Long id);
	void update(String firstName, String lastName, Long id);
}
