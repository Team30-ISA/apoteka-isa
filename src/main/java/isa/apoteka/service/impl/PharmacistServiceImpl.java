package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Examination;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.FilteredDTO;
import isa.apoteka.dto.PatientDTO;
import isa.apoteka.dto.SearchFilterDTO;
import isa.apoteka.repository.ExamintaionRepository;
import isa.apoteka.repository.PharmacistRepository;
import isa.apoteka.service.PharmacistGradeService;
import isa.apoteka.service.PharmacistService;
import isa.apoteka.service.PharmacistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class PharmacistServiceImpl implements PharmacistService{

	private PharmacistRepository pharmacistRepository;
	private ExamintaionRepository examinationRepository;
	
	private PharmacistWorkCalendarService pharmWorkCalendarService;
	private PharmacistGradeService pharmGradeService;
	
	@Autowired
	public PharmacistServiceImpl(PharmacistRepository pharmacistRepository, PharmacistWorkCalendarService pharmWorkCalendarService, PharmacistGradeService pharmGradeService, ExamintaionRepository examinationRepository) {
		this.pharmacistRepository = pharmacistRepository;
		this.examinationRepository = examinationRepository;
		this.pharmWorkCalendarService = pharmWorkCalendarService;
		this.pharmGradeService = pharmGradeService;
	}
	
	@Override
	@Transactional(readOnly = false)
	public void firePharm(Long pharmId) {
		pharmWorkCalendarService.deletePharmWorkCalendarByPharm(pharmId);
		pharmacistRepository.firePharm(pharmId);		
	}
	@Override
	@Transactional(readOnly = false)
	public Pharmacist hire(Pharmacist pharmacist) {
		return pharmacistRepository.save(pharmacist);
	}
	@Override
	public Pharmacist findByUsername(String username) {
		return pharmacistRepository.findByUsername(username);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void update(String firstName, String lastName, Long id) {		
		pharmacistRepository.update(firstName, lastName,id);
	}

	@Override
	public Pharmacy getPharmPharmacy(Long pharmacistId) {
		return pharmacistRepository.getPharmacist(pharmacistId).getPharmacy();
	}

	@Override
	public Pharmacist findById(Long id) {
		return pharmacistRepository.findById(id).orElse(null);
	}
	
	public List<FilteredDTO> searchPharms(SearchFilterDTO searchPharm){
		PharmacyAdmin admin = null;
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
	     
		List<Pharmacist> pharms = new ArrayList<Pharmacist>();
		List<Pharmacist> pharmGrades = new ArrayList<Pharmacist>();
		List<FilteredDTO>  filteredPharms = new ArrayList<FilteredDTO>();
		List<String> pharmacyNames = new ArrayList<String>();
		if(admin!=null) {
			 pharms = pharmacistRepository.searchPharmsForAdmin(searchPharm.getFirstName(), searchPharm.getLastName(), admin.getPharmacy().getId());
		}else {
			 pharms = pharmacistRepository.searchPharms(searchPharm.getFirstName(), searchPharm.getLastName());
			
		}
		
		List<Pharmacist> listPharms = new ArrayList<Pharmacist>();
		Pharmacist onePharm = null;
		for (Pharmacist d : pharms) {
			
			listPharms = pharmacistRepository.worksInPharmacy(d.getId(), searchPharm.getPharmacies());
			if(listPharms.size()!=0) {
				onePharm = listPharms.get(0);
			}
			if(onePharm != null) {
				pharmGrades.add(onePharm);
			}			
			onePharm = null;
		}
		
		double ocena;
		if(searchPharm.getMaxGrade() == 0) {
			searchPharm.setMaxGrade(5);
		}
		for (Pharmacist d :pharmGrades) {
			pharmacyNames.clear();
			ocena = pharmGradeService.findGradeForPharm(d.getId());
			System.out.println(ocena);
			if(ocena >= searchPharm.getMinGrade() && ocena <= searchPharm.getMaxGrade()) {
				pharmacyNames.add(d.getPharmacy().getName());
				filteredPharms.add(new FilteredDTO(d.getFirstName(), d.getLastName(), ocena, new ArrayList<String>(pharmacyNames)));
				
			}
		}
		
		return filteredPharms; 
	}

	@Override
	public List<PatientDTO> findAllExaminedPatients(Long pharmacistId) {
		List<PatientDTO> patients = new ArrayList<PatientDTO>();
		List<Examination> examintaions = examinationRepository.findAllByPharmId(pharmacistId);
		for(Examination e : examintaions) {
			if(e.getReport() == null || e.getReport().equals(""))
				continue;
			if(e.getPatient() != null) {
				PatientDTO dto = new PatientDTO(e.getPatient());
				if(!patients.contains(dto)) {
					patients.add(dto);
				}
			}
		}
		return patients;
	}

	@Override
	public void save(Pharmacist pharmacist) {
		pharmacistRepository.save(pharmacist);
	}

}
