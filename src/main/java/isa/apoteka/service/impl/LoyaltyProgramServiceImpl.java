package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.LoyaltyProgram;
import isa.apoteka.repository.LoyaltyProgramRepository;
import isa.apoteka.service.LoyaltyProgramService;

@Service
public class LoyaltyProgramServiceImpl implements LoyaltyProgramService {

	private LoyaltyProgramRepository loyaltyProgramRepository;
	
	@Autowired
	public LoyaltyProgramServiceImpl(LoyaltyProgramRepository loyaltyProgramRepository) {
		this.loyaltyProgramRepository = loyaltyProgramRepository;
	}
	
	@Override
	public LoyaltyProgram findById(Long id) {
		 return loyaltyProgramRepository.findById(id).orElseThrow();
	}

	@Override
	public List<LoyaltyProgram> findAll() {
		return loyaltyProgramRepository.findAll();
	}

	@Override
	public LoyaltyProgram save(LoyaltyProgram loyaltyProgram) {
		return this.loyaltyProgramRepository.save(loyaltyProgram);
	}
}
