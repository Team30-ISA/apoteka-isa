package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import isa.apoteka.domain.*;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.repository.CounselingRepository;
import isa.apoteka.repository.ExamintaionRepository;
import isa.apoteka.repository.ReservedMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

	@Autowired
	private ExamintaionRepository examintaionRepository;

	@Autowired
	private CounselingRepository counselingRepository;

	@Autowired
	private ReservedMedicineRepository reservedMedicineRepository;

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
	public Pharmacy create(PharmacyDTO pharmacyDTO) {
		Pharmacy pharmacy = new Pharmacy(pharmacyDTO);
		pharmacyRepository.save(pharmacy);
		return pharmacy;
	}

	@Override
	public List<PharmacyAdmin> getPharmacyAdminsForPharmacy(Long id) {
		return pharmacyRepository.getOne(id).getPharmacyAdmins();
	}
	public Pharmacy findById(Long id) {
		return pharmacyRepository.findById(id).orElse(null);
	}

	@Override
	public void update(PharmacyDTO pharmacyDTO) {
		pharmacyRepository.update(pharmacyDTO.getId(),pharmacyDTO.getName(),pharmacyDTO.getAddress(),pharmacyDTO.getCity(),pharmacyDTO.getDescription());
		
	}

	@Override
	public List<Pharmacy> getPharmaciesAsociatedWithUser(Long id) {
		List<Examination> examinations = examintaionRepository.findByPatientId(id);
		List<Counseling> counselings = counselingRepository.findByPatientId(id);

		List<Pharmacy> pharmacies = examinations.stream().map(e -> e.getPharmacistWorkCalendar().getPharmacy()).collect(Collectors.toList());

		for(Counseling e : counselings) {
			if(pharmacies.stream().anyMatch(p -> p.getId().equals(e.getDermatologistWorkCalendar().getPharmacy().getId())))
				continue;
			pharmacies.add(e.getDermatologistWorkCalendar().getPharmacy());
		}

		List<ReservedMedicine> reservedMedicines = reservedMedicineRepository.findByPatientId(id);

		for(ReservedMedicine r : reservedMedicines) {
			if(pharmacies.stream().anyMatch(p -> p.getId().equals(r.getPharmacy().getId())))
				continue;
			pharmacies.add(r.getPharmacy());
		}

		return pharmacies;
	}
}
