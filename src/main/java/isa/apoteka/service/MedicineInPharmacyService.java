package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.MedicineInPharmacy;

public interface MedicineInPharmacyService {
	void addMedicine(Long medicineId, Long pharmacyId);
	void deleteMedication(Long medicineId, Long pharmacyId);
	int getQuantityForMedicineInPharmacy(Long medId, Long pharmacyId);
	List<MedicineInPharmacy> getMedicineInPharmacy(Long pharmacyId);
	List<MedicineInPharmacy> searchMedicineInPharmacy(Long pharmacyId, String name);
	List<MedicineInPharmacy> getAvailableMedicineInPharmacy(Long id);
	void changeQuantity(Long id, int quantity, Long long1);

}
