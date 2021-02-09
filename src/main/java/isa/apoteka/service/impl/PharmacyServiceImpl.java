package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.MedicineDisplay;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.repository.PharmacyRepository;
import isa.apoteka.service.PharmacyService;

@Service
public class PharmacyServiceImpl implements PharmacyService{
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

	@Override
	public List<Dermatologist> findAllDermsNotWorkingInPharmacy(Long id) {
		return pharmacyRepository.findAllDermsNotWorkingInPharmacy(id);
	}
		
	public List<Dermatologist> searchDermsWorkingInPharmacy(Long id, String firstName, String lastName){
		return pharmacyRepository.searchDermsWorkingInPharmacy(id, firstName, lastName);
	}
	
	public List<Pharmacist> searchPharmsWorkingInPharmacy(Long id, String firstName, String lastName){
		return pharmacyRepository.searchPharmsWorkingInPharmacy(id, firstName, lastName);
	}
	
	public Medicine searchMedicineInPharmacy(Long id, String name){
		name = name.toLowerCase();
		String s = name.substring(0, 1).toUpperCase();
	    String nameCapitalized = s + name.substring(1);
		return pharmacyRepository.searchMedicineInPharmacy(id, nameCapitalized);
	}
	
	public void updateMedicineInPharmacy(Long pharmId, Long medId, int quantity) {
		pharmacyRepository.updateMedicineInPharmacy(pharmId, medId, quantity);
	}

	@Override
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElse(null);
	}

	@Override
	public void update(PharmacyDTO pharmacyDTO) {
		pharmacyRepository.update(pharmacyDTO.getId(),pharmacyDTO.getName(),pharmacyDTO.getAddress(),pharmacyDTO.getCity(),pharmacyDTO.getDescription());
		
	}
}
