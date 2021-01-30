package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;
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
		return dermatologistRepository.findById(id).orElse(null);
	}

	@Override
	public Dermatologist findByName(String name) {
		return dermatologistRepository.findOneByfirstName(name);
	}

	@Override
	public List<PharmacyDTO> getDermPharmacies(Long dermatologistId) {
		return pharmacyListToPharmacyDTOlist(dermatologistRepository.getDermPharmacies(dermatologistId));
	}
	
	public List<PharmacyDTO> pharmacyListToPharmacyDTOlist(List<Pharmacy> pharmacies){
		List<PharmacyDTO> dtos = new ArrayList<>();
		for(Pharmacy p : pharmacies)
			dtos.add(new PharmacyDTO(p));
		return dtos;
	}

	@Override
	public void hireDerm(Long dermId, Long pharmacyId) {
		dermatologistRepository.hireDerm(dermId, pharmacyId);
		
	}
	
	@Override
	public void fireDerm(Long dermId, Long pharmacyId) {
		dermatologistRepository.fireDerm(dermId, pharmacyId);
		
	}
	
}
