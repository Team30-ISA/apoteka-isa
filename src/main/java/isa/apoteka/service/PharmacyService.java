package isa.apoteka.service;

import java.util.List;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.MedicineDisplay;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.FilteredDTO;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.dto.SearchFilterDTO;


public interface PharmacyService {

	
	public Pharmacy findOne(Long id);

	public List<Pharmacy> findAll();

	public Pharmacy save(Pharmacy pharmacy);

	public void remove(Long id);
	
	public Pharmacy findByName(String name);
	
	public List<Dermatologist> findAllDermsWorkingInPharmacy(Long id);
	
	public List<Dermatologist> searchDermsWorkingInPharmacy(Long id, String firstName, String lastName);
	
	public List<Dermatologist> findAllDermsNotWorkingInPharmacy(Long id);
	
	public List<Pharmacist> findAllPharmsWorkingInPharmacy(Long id);
	
	public List<Pharmacist> searchPharmsWorkingInPharmacy(Long id, String firstName, String lastName);
	
	public Medicine searchMedicineInPharmacy(Long id, String name);
	
	void updateMedicineInPharmacy(Long pharmId, Long medId, int quantity);

    public Pharmacy create(PharmacyDTO pharmacyDTO);
}
