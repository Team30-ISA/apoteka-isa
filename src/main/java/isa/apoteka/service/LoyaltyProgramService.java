package isa.apoteka.service;
import java.util.List;

import isa.apoteka.domain.LoyaltyProgram;

public interface LoyaltyProgramService {
	
	LoyaltyProgram findById(Long id);
	List<LoyaltyProgram> findAll ();
	LoyaltyProgram save(LoyaltyProgram loyaltyProgram);
}
