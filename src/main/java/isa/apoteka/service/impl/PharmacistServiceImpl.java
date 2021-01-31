package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.repository.PharmacistRepository;
import isa.apoteka.service.PharmacistService;

@Service
public class PharmacistServiceImpl implements PharmacistService {
	
	@Autowired
	private PharmacistRepository pharmacistRepository;

	@Override
	public Pharmacy getPharmPharmacy(Long pharmacistId) {
		return pharmacistRepository.getPharmacist(pharmacistId).getPharmacy();
	}

	@Override
	public Pharmacist findById(Long id) {
		return pharmacistRepository.findById(id).orElse(null);
	}

}
