package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.MedicineInPharmacy;
import isa.apoteka.domain.MedicinePrice;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.MedicineDTO;
import isa.apoteka.dto.MedicineNameDTO;
import isa.apoteka.repository.MedicineRepository;
import isa.apoteka.service.MedicineInPharmacyService;
import isa.apoteka.service.MedicinePriceService;
import isa.apoteka.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {
	
	
	private MedicineRepository medicineRepository;
	private MedicinePriceService medPriceService;
	private MedicineInPharmacyService medInPharmacyService; 

	@Autowired
	public MedicineServiceImpl(MedicineRepository medicineRepository, MedicinePriceService medPriceService, MedicineInPharmacyService medInPharmacyService) {
		this.medicineRepository = medicineRepository;
		this.medPriceService = medPriceService;
		this.medInPharmacyService = medInPharmacyService;
	}
	
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

	@Override
	public List<MedicineDTO> findAllMedicineInPharmacy() {
		List<MedicineDTO> dto = new ArrayList<MedicineDTO>();
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MedicineInPharmacy> medicineInPharmacy = medInPharmacyService.getMedicineInPharmacy(admin.getPharmacy().getId());
		
		for(MedicineInPharmacy m : medicineInPharmacy) { 			
			Long medId = m.getMedicine().getId();
			String name = m.getMedicine().getName();
			int quantity = m.getQuantity();
			MedicinePrice medPrice = medPriceService.findMedicinePrice(admin.getPharmacy().getId(), medId);
			int price;
			if(medPrice.getPrice() == null) {
				price = 0;
			}else {
				price = medPrice.getPrice();
			}
			Date start = medPrice.getStartOfPrice();
			Date end = medPrice.getEndOfPrice();
			dto.add(new MedicineDTO(medId, name, quantity, price, start,end ));
		}
		
		return dto;
	}

	@Override
	public List<MedicineDTO> searchMedicineInPharmacy(String name) {
		List<MedicineDTO> dto = new ArrayList<MedicineDTO>();
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MedicineInPharmacy> medicineInPharmacy = medInPharmacyService.searchMedicineInPharmacy(admin.getPharmacy().getId(), name);
		
		for(MedicineInPharmacy m : medicineInPharmacy) { 			
			Long medId = m.getMedicine().getId();
			String medName = m.getMedicine().getName();
			int quantity = m.getQuantity();
			MedicinePrice medPrice = medPriceService.findMedicinePrice(admin.getPharmacy().getId(), medId);
			int price;
			if(medPrice.getPrice() == null) {
				price = 0;
			}else {
				price = medPrice.getPrice();
			}
			Date start = medPrice.getStartOfPrice();
			Date end = medPrice.getEndOfPrice();
			dto.add(new MedicineDTO(medId, medName, quantity, price, start,end ));
		}
		
		return dto;
	}

	@Override
	public List<MedicineNameDTO> findAllMedicineNotInPharmacy() {
		
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Medicine> med = medicineRepository.findAllMedicineNotInPharmacy(admin.getPharmacy().getId());
		List<MedicineNameDTO> dto = new ArrayList<MedicineNameDTO>();
			for(Medicine m : med) {
				dto.add(new MedicineNameDTO(m.getId(),m.getName()));
			}
			
		return dto;
	}

}
