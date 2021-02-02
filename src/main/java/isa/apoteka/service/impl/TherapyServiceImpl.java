package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Therapy;
import isa.apoteka.repository.TherapyRepository;
import isa.apoteka.service.TherapyService;

@Service
public class TherapyServiceImpl implements TherapyService {

	@Autowired
	private TherapyRepository therapyRepository;

	@Override
	public Therapy save(Therapy therapy) {
		return therapyRepository.save(therapy);
	}
	
	@Override
	public void deleteById(Long id) {
		therapyRepository.deleteById(id);
	}

}
