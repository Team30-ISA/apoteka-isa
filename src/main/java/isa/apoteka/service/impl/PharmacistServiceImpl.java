package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.repository.PharmacistRepository;
import isa.apoteka.service.PharmacistService;

@Service
public class PharmacistServiceImpl implements PharmacistService {
	@Autowired
	private PharmacistRepository pharmacistRepository;
	
	@Override
	public void update(String firstName, String lastName, Long id) {		
		pharmacistRepository.update(firstName, lastName,id);
	}
}
