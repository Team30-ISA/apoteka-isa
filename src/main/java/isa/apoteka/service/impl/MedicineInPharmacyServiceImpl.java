package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.MedicineInPharmacy;
import isa.apoteka.domain.MedicinePrice;
import isa.apoteka.repository.MedicineInPharmacyRepository;
import isa.apoteka.service.MedicineInPharmacyService;
import isa.apoteka.service.MedicinePriceService;

@Service
@Transactional(readOnly = true)
public class MedicineInPharmacyServiceImpl implements MedicineInPharmacyService{

	private MedicineInPharmacyRepository medInPharmacyRepository;
	private MedicinePriceService medicinePriceService;
	
	@Autowired
	public MedicineInPharmacyServiceImpl(MedicineInPharmacyRepository medInPharmacyRepository, MedicinePriceService medicinePriceService) {
		this.medInPharmacyRepository = medInPharmacyRepository;
		this.medicinePriceService = medicinePriceService;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void addMedicine(Long medicineId, Long pharmacyId) {
		medInPharmacyRepository.addNewMedicine(medicineId, pharmacyId);
		medicinePriceService.addMedicinePrice(medicineId, pharmacyId);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteMedication(Long medicineId, Long pharmacyId) {
		medInPharmacyRepository.deleteMedication(medicineId, pharmacyId);
	}

	@Override
	public int getQuantityForMedicineInPharmacy(Long medId, Long pharmacyId) {
		return medInPharmacyRepository.getQuantityForMedicineInPharmacy(medId, pharmacyId).getQuantity();
	}

	@Override
	public List<MedicineInPharmacy> getMedicineInPharmacy(Long pharmacyId) {
		return medInPharmacyRepository.getMedicineInPharmacy(pharmacyId);
	}
	
	@Override
	public List<MedicineInPharmacy> searchMedicineInPharmacy(Long pharmacyId, String name) {
		return medInPharmacyRepository.searchMedicineInPharmacy(pharmacyId, name);
	}

}
