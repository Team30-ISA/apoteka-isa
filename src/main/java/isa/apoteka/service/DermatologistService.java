package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.PharmacyDTO;

public interface DermatologistService {
	String getLogged();
	Dermatologist findById(Long id);
	Dermatologist findByName(String name);
	List<PharmacyDTO> getDermPharmacies(Long dermatologistId);
}
