package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.repository.PharmacistRepository;
import isa.apoteka.service.PharmacistService;
import isa.apoteka.service.PharmacistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class PharmacistServiceImpl implements PharmacistService{

	private PharmacistRepository pharmacistRepository;
	private PharmacistWorkCalendarService pharmWorkCalendarService;
	
	@Autowired
	public PharmacistServiceImpl(PharmacistRepository pharmacistRepository, PharmacistWorkCalendarService pharmWorkCalendarService) {
		this.pharmacistRepository = pharmacistRepository;
		this.pharmWorkCalendarService = pharmWorkCalendarService;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void firePharm(Long pharmId) {
		pharmWorkCalendarService.deletePharmWorkCalendarByPharm(pharmId);
		pharmacistRepository.firePharm(pharmId);		
	}
	@Override
	@Transactional(readOnly = false)
	public Pharmacist hire(Pharmacist pharmacist) {
		return pharmacistRepository.save(pharmacist);
	}
	@Override
	public Pharmacist findByUsername(String username) {
		return pharmacistRepository.findByUsername(username);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void update(String firstName, String lastName, Long id) {		
		pharmacistRepository.update(firstName, lastName,id);
	}

	@Override
	public Pharmacy getPharmPharmacy(Long pharmacistId) {
		return pharmacistRepository.getPharmacist(pharmacistId).getPharmacy();
	}

	@Override
	public Pharmacist findById(Long id) {
		return pharmacistRepository.findById(id).orElse(null);
	}

}
