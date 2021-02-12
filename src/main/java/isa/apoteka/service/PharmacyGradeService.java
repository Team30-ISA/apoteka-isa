package isa.apoteka.service;

import isa.apoteka.dto.BusinessDTO;

public interface PharmacyGradeService {

	double findGradeForPharmacy(Long id);

	BusinessDTO getPercentage(Long id);
}
