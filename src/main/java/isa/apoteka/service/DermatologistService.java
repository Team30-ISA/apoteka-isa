package isa.apoteka.service;

import isa.apoteka.domain.Dermatologist;

public interface DermatologistService {
	String getLogged();
	Dermatologist findById(Long id);
	Dermatologist findByName(String name);
}
