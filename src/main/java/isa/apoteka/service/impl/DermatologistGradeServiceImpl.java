package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.dto.DermGradeDTO;
import isa.apoteka.repository.DermatologistGradeRepository;
import isa.apoteka.service.DermatologistGradeService;
import isa.apoteka.service.DermatologistService;

@Service
public class DermatologistGradeServiceImpl implements DermatologistGradeService{

	private DermatologistGradeRepository dermGradeRepository;

	@Autowired 
	public DermatologistGradeServiceImpl(DermatologistGradeRepository dermGradeRepository) {
		this.dermGradeRepository = dermGradeRepository;
	}
	
	
	public double findGradeForDerm(Long id){
		if(dermGradeRepository.findAvgOfGrades(id) == null) {
			return 0;
		}
		return dermGradeRepository.findAvgOfGrades(id);
	}
}
