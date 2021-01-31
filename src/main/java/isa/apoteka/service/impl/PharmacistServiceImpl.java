package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.repository.PharmacistRepository;
import isa.apoteka.service.PharmacistService;

@Service
public class PharmacistServiceImpl implements PharmacistService{

	@Autowired
	private PharmacistRepository pharmacistRepository;
	@Override
	public void firePharm(Long pharmId) {
		pharmacistRepository.firePharm(pharmId);		
	}
	@Override
	public Pharmacist hire(Pharmacist pharmacist) {
		return pharmacistRepository.save(pharmacist);
	}

}
