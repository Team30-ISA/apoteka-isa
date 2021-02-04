package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.MedicinePrice;
import isa.apoteka.dto.ChangePriceDTO;
import isa.apoteka.dto.MedicineDTO;

public interface MedicinePriceService {
	MedicinePrice findMedicinePrice(Long pharmacyId, Long medId);
	void addMedicinePrice(Long medicineId, Long pharmacyId);
	void changePrice(ChangePriceDTO dto);
}
