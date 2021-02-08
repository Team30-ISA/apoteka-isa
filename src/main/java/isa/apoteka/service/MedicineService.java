package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Medicine;
import isa.apoteka.dto.MedicineDTO;
import isa.apoteka.dto.MedicineNameDTO;

public interface MedicineService {
	Medicine findOne(Long id);
	List<Medicine> findAll();
	List<Medicine> searchMedicinesByName(String name);
	Boolean isPatientAllergic(Long patientId, Long medicineId);
	List<Medicine> getSubstitutesOfMedicine(Long id);
	Boolean isMedicineAvailableInPharmacy(Long medicineId, Long pharmacyId);
	List<MedicineDTO> findAllMedicineInPharmacy();
	List<MedicineNameDTO> findAllMedicineNotInPharmacy();
	List<MedicineDTO> searchMedicineInPharmacy(String name);
	List<MedicineNameDTO> findAllMedicine();
	List<MedicineDTO> findAllMedicineAvailableInPharmacy();
}
