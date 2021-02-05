package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.MedicinePrice;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.ChangePriceDTO;
import isa.apoteka.dto.MedicineDTO;
import isa.apoteka.repository.MedicinePriceRepository;
import isa.apoteka.service.MedicinePriceService;

@Service
@Transactional(readOnly = true)
public class MedicinePriceServiceImpl implements MedicinePriceService{

	private MedicinePriceRepository medPriceRepository;
	
	@Autowired
	public MedicinePriceServiceImpl(MedicinePriceRepository medPriceRepository) {
		this.medPriceRepository = medPriceRepository;
	}
	
	
	@Override
	public MedicinePrice findMedicinePrice(Long pharmacyId, Long medId) {
		List<MedicinePrice> med = medPriceRepository.findMedicinePrice(pharmacyId, medId);	
		if(med.size()>1 && med.get(0).getStartOfPrice() == null) {
			return med.get(1);
		}else {
			return med.get(0);
		}
		
	}


	@Override
	@Transactional(readOnly = false)
	public void addMedicinePrice(Long medicineId, Long pharmacyId) {
		medPriceRepository.addMedicinePrice(medicineId, pharmacyId);
		
	}


	@Override
	@Transactional(readOnly = false)
	public void changePrice(ChangePriceDTO dto) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		medPriceRepository.changePrice(dto.getMedicineId(), admin.getPharmacy().getId(), dto.getNewPrice(), dto.getStartOfPrice(), dto.getEndOfPrice());
	}
}
