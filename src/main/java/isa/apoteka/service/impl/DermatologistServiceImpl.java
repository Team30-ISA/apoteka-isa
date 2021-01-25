package isa.apoteka.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.repository.DermatologistRepository;
import isa.apoteka.service.DermatologistService;

@Service
public class DermatologistServiceImpl implements DermatologistService {
	@Autowired
	private DermatologistRepository dermatologistRepository;

	@Override
	public String getLogged() {
		return SecurityContextHolder.getContext().getAuthentication().toString();
	}
	
	@Override
	public Dermatologist findById(Long id) {
		Dermatologist derm = dermatologistRepository.findById(id).orElse(null);
		return derm;
	}

	@Override
	public Dermatologist findByName(String name) {
		Dermatologist derm = dermatologistRepository.findOneByfirstName(name);
		return derm;
	}
	
	
}
