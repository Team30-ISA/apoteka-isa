package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.DrugForm;
import isa.apoteka.domain.DrugType;
import isa.apoteka.domain.Medicine;
import isa.apoteka.dto.*;

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
    Medicine create(MedicineCreateDTO medicineDTO);

	List<DrugType> getAllTypes();

	List<DrugForm> getAllForms();
	List<MedicineDTO> findAllMedicineAvailableInPharmacy(Long pharmacyId);

	List<MedicinePreviewDTO> getAllMedicines(String medicineName);

	MedicineSpecificationDTO getMedicine(Long id);
}
