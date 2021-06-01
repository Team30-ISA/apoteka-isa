package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import isa.apoteka.dto.LoyaltyProgramDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.LoyaltyProgram;
import isa.apoteka.domain.Patient;
import isa.apoteka.repository.LoyaltyProgramRepository;
import isa.apoteka.repository.PatientRepository;
import isa.apoteka.service.LoyaltyProgramService;

@Service
public class LoyaltyProgramServiceImpl implements LoyaltyProgramService {

	private LoyaltyProgramRepository loyaltyProgramRepository;
	private PatientRepository patientRepository;
	
	@Autowired
	public LoyaltyProgramServiceImpl(LoyaltyProgramRepository loyaltyProgramRepository, PatientRepository patientRepository) {
		this.loyaltyProgramRepository = loyaltyProgramRepository;
		this.patientRepository = patientRepository;
	}
	
	@Override
	public LoyaltyProgram findById(Long id) {
		 return loyaltyProgramRepository.getOne(id);
	}

	@Override
	public List<LoyaltyProgramDTO> getAllLoyaltyPrograms() {
		List<LoyaltyProgram> loyaltyPrograms = this.loyaltyProgramRepository.findAll();
		List<LoyaltyProgramDTO> loyaltyProgramDTOs = new ArrayList<LoyaltyProgramDTO>();
		
		for(LoyaltyProgram loyaltyProgram : loyaltyPrograms)
			loyaltyProgramDTOs.add(new LoyaltyProgramDTO(loyaltyProgram));
			
		return loyaltyProgramDTOs;
	}

	@Override
	public LoyaltyProgramDTO save(LoyaltyProgramDTO loyaltyProgram) {
		LoyaltyProgram newLoyaltyProgram = new LoyaltyProgram(loyaltyProgram);
		this.loyaltyProgramRepository.save(newLoyaltyProgram);
		return loyaltyProgram;
	}
	
	@Override
	public void updatePatientsLoyaltyPoints(Integer points) {
	    try {
	    	Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    patient.setPoints(patient.getPoints() + points);
		    String category = patient.getLoyaltyCategory();
	    	LoyaltyProgram loyaltyProgram = findAll().get(0);
	        if(category.equals("REGULAR")) {
	        	if(patient.getPoints() >= loyaltyProgram.getSilverMinPoints()) {
	        		patient.setDiscount(loyaltyProgram.getSilverDiscount());
	        		patient.setLoyaltyCategory("SILVER");
	              	}
	            }
	            else if(category.equals("SILVER")) {
	                if(patient.getPoints() >= loyaltyProgram.getGoldenMinPoints()) {
	                    patient.setDiscount(loyaltyProgram.getGoldenDiscount());
	                    patient.setLoyaltyCategory("GOLD");
	                }
	            }
	            patientRepository.save(patient);
	        }
	        catch(Exception e ) {
	        	System.out.println("catch");
	        }
	    }

	@Override
	public LoyaltyProgram update(LoyaltyProgramDTO loyaltyProgram) throws Exception {
        LoyaltyProgram program = this.loyaltyProgramRepository.getOne(loyaltyProgram.getId());
		
        program.setCounselingPoints(loyaltyProgram.getCounselingPoints());
        program.setExaminationPoints(loyaltyProgram.getExaminationPoints());
        program.setGoldenDiscount(loyaltyProgram.getGoldenDiscount());
        program.setGoldenMinPoints(loyaltyProgram.getGoldenMinPoints());
        program.setSilverDiscount(loyaltyProgram.getSilverDiscount());
        program.setSilverMinPoints(loyaltyProgram.getSilverMinPoints());
        program.setRegularDiscount(loyaltyProgram.getRegularDiscount());
        program.setRegularMinPoints(loyaltyProgram.getRegularMinPoints());
        
        this.loyaltyProgramRepository.save(program);
        
        return program;
	}

	@Override
	public LoyaltyProgramDTO getLoyaltyProgram() {
		return this.getAllLoyaltyPrograms().get(0);
	}

	@Override
	public List<LoyaltyProgram> findAll() {
		return this.loyaltyProgramRepository.findAll();
	}
}
