package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.PharmacyDTO;
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

	@Override
	public List<PharmacyDTO> getDermPharmacies(Long dermatologistId) {
		return pharmacyListToPharmacyDTOlist(dermatologistRepository.getDermPharmacies(dermatologistId));
	}
	
	public List<PharmacyDTO> pharmacyListToPharmacyDTOlist(List<Pharmacy> pharmacies){
		List<PharmacyDTO> dtos = new ArrayList<PharmacyDTO>();
		for(Pharmacy p : pharmacies)
			dtos.add(new PharmacyDTO(p));
		return dtos;
	}
	
	
}
