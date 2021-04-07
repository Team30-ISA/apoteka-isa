package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.MedicinePrice;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ChangePriceDTO;
import isa.apoteka.dto.ChoosenPharmacyDTO;
import isa.apoteka.dto.MedicineDTO;

public interface MedicinePriceService {
	MedicinePrice findMedicinePrice(Long pharmacyId, Long medId);
	void addMedicinePrice(Long medicineId, Long pharmacyId);
	void changePrice(ChangePriceDTO dto);
	Integer getPrice(Long medId, Long pharmId, Date date);
	Boolean updateMedicineQuantityEreceipt(ChoosenPharmacyDTO choosenPharmacy);
	public List<MedicinePrice> findAll();
}
