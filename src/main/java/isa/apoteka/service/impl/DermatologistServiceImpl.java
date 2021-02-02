package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.repository.DermatologistRepository;
import isa.apoteka.service.DermatologistService;
import isa.apoteka.service.DermatologistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class DermatologistServiceImpl implements DermatologistService {
	
	private DermatologistRepository dermatologistRepository;
	private DermatologistWorkCalendarService dermWCService;
	
	@Autowired
	public DermatologistServiceImpl(DermatologistRepository dermatologistRepository, DermatologistWorkCalendarService dermWCService) {
		this.dermatologistRepository = dermatologistRepository;
		this.dermWCService = dermWCService;
	}
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
	@Transactional(readOnly = false)
	public void hireDerm(Long dermId, Long pharmacyId) {
		dermatologistRepository.hireDerm(dermId, pharmacyId);
		
	}
	
	@Override
	@Transactional(readOnly = false)
	public void fireDerm(Long dermId, Long pharmacyId) {
		
		dermWCService.deleteDermWorkCalendarByDerm(dermId);
		dermatologistRepository.fireDerm(dermId, pharmacyId);
		
	}
	
	@Override
	@Transactional(readOnly = false)
	public void update(String firstName, String lastName, Long id) {		
		dermatologistRepository.update(firstName, lastName,id);
	}
	
}
