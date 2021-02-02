package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Medicine;

public interface MedicineService {
	Medicine findOne(Long id);
	List<Medicine> findAll();
	List<Medicine> searchMedicinesByName(String name);
	Boolean isPatientAllergic(Long patientId, Long medicineId);
	List<Medicine> getSubstitutesOfMedicine(Long id);
	Boolean isMedicineAvailableInPharmacy(Long medicineId, Long pharmacyId);
}
