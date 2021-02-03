package isa.apoteka.service;

import isa.apoteka.domain.Therapy;

public interface TherapyService {
	
	Therapy save(Therapy therapy);
	void deleteById(Long id);
}
