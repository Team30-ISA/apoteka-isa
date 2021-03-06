package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.MedicineInPharmacy;
import isa.apoteka.domain.MedicineQuantity;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.MedicineForSupplyDTO;
import isa.apoteka.dto.MedicineQuantityDTO;
import isa.apoteka.repository.MedicineQuantityRepository;
import isa.apoteka.repository.PharmacyAdminRepository;
import isa.apoteka.service.MedicineInPharmacyService;
import isa.apoteka.service.MedicineQuantityService;

@Service
@Transactional(readOnly = true)
public class MedicineQuantityServiceImpl implements MedicineQuantityService{

	private MedicineQuantityRepository medQuantityReposiotry;
	private MedicineInPharmacyService medInPharmacyService;
	private PharmacyAdminRepository pharmacyAdminRepository;
	@Autowired
	public MedicineQuantityServiceImpl(MedicineQuantityRepository medQuantityReposiotry, MedicineInPharmacyService medInPharmacyService, PharmacyAdminRepository pharmacyAdminRepository) {
		this.medQuantityReposiotry = medQuantityReposiotry;
		this.medInPharmacyService = medInPharmacyService;
		this.pharmacyAdminRepository = pharmacyAdminRepository;
	}
	@Override
	@Transactional(readOnly = false)
	public Boolean insert(List<MedicineQuantityDTO> dto) {
		//PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PharmacyAdmin admin = pharmacyAdminRepository.getOne(Long.valueOf(11));
		for(MedicineQuantityDTO mq : dto) {
			medQuantityReposiotry.insertNew(mq.getId(),mq.getErrandId(),mq.getQuantity());
			medInPharmacyService.addMedicine(mq.getId(), admin.getPharmacy().getId());
		}
		return true;
	}
	@Override
	public List<MedicineForSupplyDTO> getMedicineForErrand(Long errandId) {
		List<MedicineQuantity> med = medQuantityReposiotry.getMedicineForErrand(errandId);
		List<MedicineForSupplyDTO> dto = new ArrayList<MedicineForSupplyDTO>();
		for(MedicineQuantity mq : med) {
			dto.add(new MedicineForSupplyDTO(mq.getMedicine().getId(), mq.getMedicine().getName(),mq.getQuantity()));
		}
		return dto;
	}

	@Transactional(readOnly = false)
	public Boolean changeQuantity(Long errandId) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MedicineQuantity> med = medQuantityReposiotry.getMedicineForErrand(errandId);
		for(MedicineQuantity mq : med) {
			medInPharmacyService.changeQuantity(mq.getMedicine().getId(), mq.getQuantity(), admin.getPharmacy().getId());
		}
		return true;
	}
	@Override
	public void deleteMedQuantity(Long errandId, PharmacyAdmin admin) {
		List<MedicineQuantity> med = medQuantityReposiotry.getMedicineForErrand(errandId);
		for(MedicineQuantity mq : med) {
			medInPharmacyService.deleteMedication(mq.getMedicine().getId(), admin.getPharmacy().getId());
		}
		
		medQuantityReposiotry.deleteAll(med);
		
	}
}
