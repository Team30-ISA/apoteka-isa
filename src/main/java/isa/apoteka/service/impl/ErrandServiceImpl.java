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
import isa.apoteka.service.ErrandService;
@Service
@Transactional(readOnly = true)
public class ErrandServiceImpl implements ErrandService{

	private ErrandRepository errandRepository;
	@Autowired
	public ErrandServiceImpl(ErrandRepository errandRepository) {
		this.errandRepository = errandRepository;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Long save(Date deadline) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(deadline);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, 1);
		Date startDate = calendar.getTime();
		
		
		Errand e = new Errand();
		e.setFinished(false);
		e.setCreationTime(new Date());
		e.setDeadline(startDate);
		e.setPharmacy(admin.getPharmacy());
		e.setMedicineForOrder(null);
		Errand errand = errandRepository.save(e);
		return errand.getId();
	}

	@Override
	public List<Errand> findAllErrands() {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return errandRepository.findAllByPharmacy(admin.getPharmacy().getId());
	}

	@Override
	public List<ErrandPreviewDTO> findAllValidErrands() {
		return errandRepository.findAll().stream().filter(e -> e.getDeadline().after(new Date())).collect(Collectors.toList()).stream().map(e -> new ErrandPreviewDTO(e)).collect(Collectors.toList());
	}

}
