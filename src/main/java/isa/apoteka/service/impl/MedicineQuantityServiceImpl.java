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
import isa.apoteka.service.MedicineInPharmacyService;
import isa.apoteka.service.MedicineQuantityService;

@Service
@Transactional(readOnly = true)
public class MedicineQuantityServiceImpl implements MedicineQuantityService{

	private MedicineQuantityRepository medQuantityReposiotry;
	private MedicineInPharmacyService medInPharmacyService;
	@Autowired
	public MedicineQuantityServiceImpl(MedicineQuantityRepository medQuantityReposiotry, MedicineInPharmacyService medInPharmacyService) {
		this.medQuantityReposiotry = medQuantityReposiotry;
		this.medInPharmacyService = medInPharmacyService;
	}
	@Override
	@Transactional(readOnly = false)
	public Boolean insert(List<MedicineQuantityDTO> dto) {
		System.out.println("*");
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

}
