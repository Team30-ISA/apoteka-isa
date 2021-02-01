package isa.apoteka.service;

import java.util.List;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;


public interface PharmacyService {

	
	public Pharmacy findOne(Long id);

	public List<Pharmacy> findAll();

	public Pharmacy save(Pharmacy pharmacy);

	public void remove(Long id);
	
	public Pharmacy findByName(String name);
	
	public List<Dermatologist> findAllDermsWorkingInPharmacy(Long id);
	
	public List<Dermatologist> findAllDermsNotWorkingInPharmacy(Long id);
	
	public List<Pharmacist> findAllPharmsWorkingInPharmacy(Long id);
	
}
