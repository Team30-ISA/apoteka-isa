package isa.apoteka.service;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.ReservedMedicine;

public interface MedicineService {
	Medicine findById(Long medId);
}
