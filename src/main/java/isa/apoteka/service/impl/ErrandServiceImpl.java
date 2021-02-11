package isa.apoteka.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Errand;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.repository.ErrandRepository;
import isa.apoteka.service.ErrandService;
import isa.apoteka.service.MedicineQuantityService;
@Service
@Transactional(readOnly = true)
public class ErrandServiceImpl implements ErrandService{

	private ErrandRepository errandRepository;
	private MedicineQuantityService medicineQuantityService;
	
	@Autowired
	public ErrandServiceImpl(ErrandRepository errandRepository, MedicineQuantityService medicineQuantityService) {
		this.errandRepository = errandRepository;
		this.medicineQuantityService = medicineQuantityService;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean save(Date deadline) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(deadline);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, 1);
		Date startDate = calendar.getTime();
		try {
			errandRepository.saveNewErrand(false,new Date(),startDate,admin.getPharmacy().getId(),admin.getId());
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public List<Errand> findAllErrands() {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return errandRepository.findAllByPharmacy(admin.getPharmacy().getId());
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Long errandId, PharmacyAdmin admin) {
		medicineQuantityService.deleteMedQuantity(errandId, admin);
		errandRepository.deleteById(errandId);
	}

}
