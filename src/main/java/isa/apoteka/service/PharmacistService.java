package isa.apoteka.service;

import isa.apoteka.domain.Pharmacist;

public interface PharmacistService {

	void firePharm(Long pharmId);
	Pharmacist hire(Pharmacist pharmacist);
}
