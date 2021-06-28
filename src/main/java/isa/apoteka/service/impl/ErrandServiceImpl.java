package isa.apoteka.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import isa.apoteka.dto.ErrandPreviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Errand;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.repository.ErrandRepository;
import isa.apoteka.repository.PharmacyAdminRepository;
import isa.apoteka.service.ErrandService;
import isa.apoteka.service.MedicineQuantityService;

@Service
@Transactional(readOnly = true)
public class ErrandServiceImpl implements ErrandService{

	private ErrandRepository errandRepository;
	private MedicineQuantityService medicineQuantityService;
	private PharmacyAdminRepository pharmacyAdminRepository;
	
	
	public ErrandServiceImpl(ErrandRepository errandRepository, MedicineQuantityService medicineQuantityService, PharmacyAdminRepository pharmacyAdminRepository) {
		this.errandRepository = errandRepository;
		this.medicineQuantityService = medicineQuantityService;
		this.pharmacyAdminRepository = pharmacyAdminRepository;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Long save(Date deadline) {
		
		try {
			//PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			PharmacyAdmin admin = pharmacyAdminRepository.getOne(Long.valueOf(11));
			Errand errand = new Errand();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(deadline);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.add(Calendar.DATE, 1);
			Date startDate = calendar.getTime();
			
			errand.setAdmin(admin);
			errand.setCreationTime(new Date());
			errand.setDeadline(startDate);
			errand.setFinished(false);
			errand.setPharmacy(admin.getPharmacy());
			errand = errandRepository.save(errand);
			return (long)errand.getId();
		}catch(Exception e) {
			return (long)-1;
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

	public List<ErrandPreviewDTO> findAllValidErrands() {
		return errandRepository.findAll().stream().filter(e -> e.getDeadline().after(new Date())).collect(Collectors.toList()).stream().map(e -> new ErrandPreviewDTO(e)).collect(Collectors.toList());
	}


}
