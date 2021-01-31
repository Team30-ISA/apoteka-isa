package isa.apoteka.service;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;

public interface PharmacistService {
	Pharmacy getPharmPharmacy(Long pharmacistId);
	Pharmacist findById(Long id);
}
