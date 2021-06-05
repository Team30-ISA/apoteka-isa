package isa.apoteka.service;
import java.util.List;

import isa.apoteka.domain.LoyaltyProgram;
import isa.apoteka.dto.LoyaltyProgramDTO;

public interface LoyaltyProgramService {
	
	LoyaltyProgram findById(Long id);
	List<LoyaltyProgram> findAll ();
	LoyaltyProgramDTO save(LoyaltyProgramDTO loyaltyProgram);
	void  updatePatientsLoyaltyPoints(Integer points);
	LoyaltyProgram update(LoyaltyProgramDTO loyaltyProgram) throws Exception;
	LoyaltyProgramDTO getLoyaltyProgram();
	List<LoyaltyProgramDTO> getAllLoyaltyPrograms();
}
