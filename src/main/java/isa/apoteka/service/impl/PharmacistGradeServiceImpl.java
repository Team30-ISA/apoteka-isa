package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.apoteka.repository.PharmacistGradeRepository;
import isa.apoteka.service.PharmacistGradeService;

@Service
public class PharmacistGradeServiceImpl implements PharmacistGradeService{

	private PharmacistGradeRepository pharmGradeRepository;

	@Autowired 
	public PharmacistGradeServiceImpl(PharmacistGradeRepository pharmGradeRepository) {
		this.pharmGradeRepository = pharmGradeRepository;
	}
	
	
	public double findGradeForPharm(Long id){
		if(pharmGradeRepository.findAvgOfGrades(id) == null) {
			return 0;
		}
		return pharmGradeRepository.findAvgOfGrades(id);
	}
}
