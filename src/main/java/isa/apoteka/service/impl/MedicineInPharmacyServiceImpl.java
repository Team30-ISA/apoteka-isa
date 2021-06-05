package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.MedicineInPharmacy;
import isa.apoteka.domain.ReservedMedicine;
import isa.apoteka.repository.MedicineInPharmacyRepository;
import isa.apoteka.service.MedicineInPharmacyService;
import isa.apoteka.service.MedicinePriceService;
import isa.apoteka.service.MedicineReservationService;

@Service
@Transactional(readOnly = true)
public class MedicineInPharmacyServiceImpl implements MedicineInPharmacyService{

	private MedicineInPharmacyRepository medInPharmacyRepository;
	private MedicinePriceService medicinePriceService;
	private MedicineReservationService medicineReservationService;
	
	@Autowired
	public MedicineInPharmacyServiceImpl(MedicineInPharmacyRepository medInPharmacyRepository, MedicinePriceService medicinePriceService, MedicineReservationService medicineReservationService) {
		this.medInPharmacyRepository = medInPharmacyRepository;
		this.medicinePriceService = medicinePriceService;
		this.medicineReservationService = medicineReservationService;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void addMedicine(Long medicineId, Long pharmacyId) {
		medInPharmacyRepository.addNewMedicine(medicineId, pharmacyId);
		medicinePriceService.addMedicinePrice(medicineId, pharmacyId);
		
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean deleteMedication(Long medicineId, Long pharmacyId) {
		List<ReservedMedicine> med = medicineReservationService.findReservationForMedicineAndPharmacyNotFinished(medicineId,pharmacyId);
		if(med != null) {
			for(ReservedMedicine r : med) {
				if(r.getApproved() == false)
					return false;
			}
		}
		
		medInPharmacyRepository.deleteMedication(medicineId, pharmacyId);
		return true;
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

	@Override
	public List<MedicineInPharmacy> getAvailableMedicineInPharmacy(Long id) {
		System.out.println(id);
		return medInPharmacyRepository.getAvailableMedicineInPharmacy(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void changeQuantity(Long id, int quantity, Long pharmacyId) {
		medInPharmacyRepository.changeQuantity(id, quantity, pharmacyId);
		
	}

}
