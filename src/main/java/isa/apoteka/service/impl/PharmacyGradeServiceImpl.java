package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.repository.PharmacistGradeRepository;
import isa.apoteka.service.PharmacyGradeService;

@Service
@Transactional(readOnly = true)
public class PharmacyGradeServiceImpl implements PharmacyGradeService{

	private PharmacistGradeRepository pharmacyGradeRepository;
	
	@Autowired
	public PharmacyGradeServiceImpl(PharmacistGradeRepository pharmacyGradeRepository) {
		this.pharmacyGradeRepository = pharmacyGradeRepository;
	}
	
	@Transactional(readOnly = true)
	public double findGradeForPharmacy(Long id){
		if(pharmacyGradeRepository.findAvgOfGrades(id) == null) {
			return 0;
		}
		return pharmacyGradeRepository.findAvgOfGrades(id);
	}
}
