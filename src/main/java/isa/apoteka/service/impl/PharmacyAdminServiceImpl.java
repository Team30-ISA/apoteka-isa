package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PatientUpdateForm;
import isa.apoteka.repository.PharmacyAdminRepository;
import isa.apoteka.service.PharmacyAdminService;

@Service
public class PharmacyAdminServiceImpl implements PharmacyAdminService{

	@Autowired
	private PharmacyAdminRepository pharmacyAdminRepository;
	
	@Override
	public void update(String firstName, String lastName, Long id) {		
		pharmacyAdminRepository.update(firstName, lastName,id);
	}
}
