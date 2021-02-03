package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Medicine;
import isa.apoteka.repository.MedicineRepository;
import isa.apoteka.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {
	
	@Autowired
	private MedicineRepository medicineRepository;

	@Override
	public List<Medicine> findAll() {
		return medicineRepository.findAll();
	}

	@Override
	public List<Medicine> searchMedicinesByName(String name) {
		return medicineRepository.searchMedicinesByName(name.toLowerCase());
	}

	@Override
	public Boolean isPatientAllergic(Long patientId, Long medicineId) {
		if(medicineRepository.isPatientAllergic(patientId, medicineId) > 0)
			return true;
		return false;
	}

	@Override
	public List<Medicine> getSubstitutesOfMedicine(Long id) {
		return medicineRepository.getSubstitutesOfMedicine(id);
	}

	@Override
	public Boolean isMedicineAvailableInPharmacy(Long medicineId, Long pharmacyId) {
		Integer quantity = medicineRepository.getQuantityOfMedicineInPharmacy(medicineId, pharmacyId);
		if(quantity == null)
			return false;
		if(quantity > 0)
			return true;
		return false;
	}

	@Override
	public Medicine findOne(Long id) {
		return medicineRepository.findById(id).orElse(null);
	}

}
