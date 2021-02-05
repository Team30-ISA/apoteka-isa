package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Patient;
import isa.apoteka.repository.MedicineRepository;
import isa.apoteka.repository.PatientRepository;
import isa.apoteka.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService{
	
	@Autowired
	private MedicineRepository medicineRepository;
	
	public Medicine findById(Long id) throws AccessDeniedException {
		Medicine m = medicineRepository.getById(id);
		return m;
	}
}
