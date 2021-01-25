package isa.apoteka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.repository.PharmacyRepository;

@Service
public class PharmacyService {

	@Autowired
	private PharmacyRepository pharmacyRepository;
	
	public Pharmacy findOne(Long id) {
		return pharmacyRepository.findById(id).orElse(null);
	}

	public List<Pharmacy> findAll() {
		return pharmacyRepository.findAll();
	}

	public Pharmacy save(Pharmacy pharmacy) {
		return pharmacyRepository.save(pharmacy);
	}

	public void remove(Long id) {
		pharmacyRepository.deleteById(id);
	}
	
	public Pharmacy findByName(String name) {
		return pharmacyRepository.findOneByName(name);
	}
	
	public List<Dermatologist> findAllDermsWorkingInPharmacy(Long id){
		return pharmacyRepository.findAllDermsWorkingInPharmacy(id);
	}
	
	public List<Pharmacist> findAllPharmsWorkingInPharmacy(Long id){
		return pharmacyRepository.findAllPharmsWorkingInPharmacy(id);
	}
	
}
