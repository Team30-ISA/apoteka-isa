package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import isa.apoteka.dto.BusinessDTO;
import isa.apoteka.repository.PharmacistGradeRepository;
import isa.apoteka.repository.PharmacyGradeRepository;
import isa.apoteka.service.PharmacyGradeService;

@Service
@Transactional(readOnly = true)
public class PharmacyGradeServiceImpl implements PharmacyGradeService{

	private PharmacyGradeRepository pharmacyGradeRepository;
	
	@Autowired
	public PharmacyGradeServiceImpl(PharmacyGradeRepository pharmacyGradeRepository) {
		this.pharmacyGradeRepository = pharmacyGradeRepository;
	}
	
	@Transactional(readOnly = true)
	public double findGradeForPharmacy(Long id){
		if(pharmacyGradeRepository.findAvgOfGrades(id) == null) {
			return 0;
		}
		return pharmacyGradeRepository.findAvgOfGrades(id);
	}

	@Override
	public BusinessDTO getPercentage(Long id) {
		BusinessDTO dto = new BusinessDTO();
		List<Double> lista = new ArrayList<Double>();
		List<Double> gotova = new ArrayList<Double>();
		int zbir = 0;
		Long broj;
		Double m;
		for(int i=1; i<=5; i++) {
			broj = pharmacyGradeRepository.findNumberOfGrades(id, i);
			System.out.println("************");
			System.out.println(broj);
			zbir += broj.intValue();
			System.out.println("************");
			System.out.println(zbir);
			lista.add(broj.doubleValue());						
		}
		if(zbir!=0) {
			for(Double l : lista) {
				m =( l / zbir)*100;
				System.out.println(l);
				gotova.add(m);
			}
		}
		dto.setPercentageOfGrades(gotova);
		return dto;
	}
}
