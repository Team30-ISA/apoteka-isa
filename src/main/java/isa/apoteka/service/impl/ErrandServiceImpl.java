package isa.apoteka.service.impl;

import java.util.Date;
import java.util.List;

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
		
		Errand e = new Errand();
		e.setCreationTime(new Date());
		e.setDeadline(deadline);
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

}
