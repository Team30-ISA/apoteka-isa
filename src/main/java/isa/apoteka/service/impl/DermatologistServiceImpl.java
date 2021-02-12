package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import isa.apoteka.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.dto.FilteredDTO;
import isa.apoteka.dto.PatientDTO;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.dto.SearchFilterDTO;
import isa.apoteka.repository.CounselingRepository;
import isa.apoteka.repository.DermatologistRepository;
import isa.apoteka.service.DermatologistGradeService;
import isa.apoteka.service.DermatologistService;
import isa.apoteka.service.DermatologistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class DermatologistServiceImpl implements DermatologistService {
	
	private DermatologistRepository dermatologistRepository;
	private CounselingRepository counselingRepository;
	private DermatologistWorkCalendarService dermWCService;
	private DermatologistGradeService dermGradeService;
	
	@Autowired
	public DermatologistServiceImpl(DermatologistRepository dermatologistRepository, DermatologistWorkCalendarService dermWCService, DermatologistGradeService dermGradeService, CounselingRepository counselingRepository) {
		this.dermatologistRepository = dermatologistRepository;
		this.counselingRepository = counselingRepository;
		this.dermWCService = dermWCService;
		this.dermGradeService = dermGradeService;
	}
	@Override
	public String getLogged() {
		return SecurityContextHolder.getContext().getAuthentication().toString();
	}
	
	@Override
	public Dermatologist findById(Long id) {
		return dermatologistRepository.findById(id).orElse(null);
	}

	@Override
	public Dermatologist findByName(String name) {
		return dermatologistRepository.findOneByfirstName(name);
	}

	@Override
	public List<PharmacyDTO> getDermPharmacies(Long dermatologistId) {
		return pharmacyListToPharmacyDTOlist(dermatologistRepository.getDermPharmacies(dermatologistId));
	}
	
	public List<PharmacyDTO> pharmacyListToPharmacyDTOlist(List<Pharmacy> pharmacies){
		List<PharmacyDTO> dtos = new ArrayList<>();
		for(Pharmacy p : pharmacies)
			dtos.add(new PharmacyDTO(p));
		return dtos;
	}

	@Override
	@Transactional(readOnly = false)
	public void hireDerm(Long dermId, Long pharmacyId) {
		dermatologistRepository.hireDerm(dermId, pharmacyId);
		
	}
	
	@Override
	@Transactional(readOnly = false)
	public void fireDerm(Long dermId, Long pharmacyId) {
		
		dermWCService.deleteDermWorkCalendarByDerm(dermId);
		dermatologistRepository.fireDerm(dermId, pharmacyId);
		
	}
	
	@Override
	@Transactional(readOnly = false)
	public void update(String firstName, String lastName, Long id) {		
		dermatologistRepository.update(firstName, lastName,id);
	}
	@Override
	public List<Dermatologist> findAll() {
		return dermatologistRepository.findAll();
	}
	
	public List<FilteredDTO> searchDerms(SearchFilterDTO searchDerm){
		PharmacyAdmin admin = null;
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
	     
		List<Dermatologist> derms = new ArrayList<Dermatologist>();
		List<Dermatologist> dermsGrades = new ArrayList<Dermatologist>();
		List<FilteredDTO>  filteredDerms = new ArrayList<FilteredDTO>();
		List<String> pharmacyNames = new ArrayList<String>();
		if(admin!=null) {
			 derms = dermatologistRepository.searchDermsForAdmin(searchDerm.getFirstName(), searchDerm.getLastName(), admin.getPharmacy().getId());
		}else {
			 derms = dermatologistRepository.searchDerms(searchDerm.getFirstName(), searchDerm.getLastName());
			
		}
		
		List<Dermatologist> listDerms = new ArrayList<Dermatologist>();
		Dermatologist oneDerm = null;
		for (Dermatologist d : derms) {
			
			listDerms = dermatologistRepository.worksInPharmacy(d.getId(), searchDerm.getPharmacies());
			if(listDerms.size()!=0) {
				oneDerm = listDerms.get(0);
			}
			if(oneDerm != null) {
				dermsGrades.add(oneDerm);
			}			
			oneDerm = null;
		}
		
		double ocena;
		if(searchDerm.getMaxGrade() == 0) {
			searchDerm.setMaxGrade(5);
		}
		for (Dermatologist d :dermsGrades) {
			pharmacyNames.clear();
			ocena = dermGradeService.findGradeForDerm(d.getId());
			System.out.println(ocena);
			if(ocena >= searchDerm.getMinGrade() && ocena <= searchDerm.getMaxGrade()) {
				for (Pharmacy p : d.getPharmacies()) {
					System.out.println("********************");
					System.out.println(p.getName());
					pharmacyNames.add(p.getName());
				}
				System.out.println(pharmacyNames.size());
				filteredDerms.add(new FilteredDTO(d.getFirstName(), d.getLastName(), ocena, new ArrayList<String>(pharmacyNames)));
				
			}
		}
		
		return filteredDerms; 
	}
	@Override
	public List<PatientDTO> findAllExaminedPatients(Long pharmacistId) {
		List<PatientDTO> patients = new ArrayList<PatientDTO>();
		List<Counseling> counselings = counselingRepository.findAllByDermId(pharmacistId);
		for(Counseling c : counselings) {
			if(c.getReport() == null || c.getReport().equals(""))
				continue;
			if(c.getPatient() != null) {
				PatientDTO dto = new PatientDTO(c.getPatient());
				if(!patients.contains(dto)) {
					patients.add(dto);
				}
			}
		}
		return patients;
	}

	@Override
	public List<Dermatologist> getAllExaminationDermatologistsByPatientId(Long id) {
		List<Counseling> counselings = counselingRepository.findByPatientId(id);
		return counselings.stream().map(c -> c.getDermatologistWorkCalendar().getDermatologist()).collect(Collectors.toList());
	}

	@Override
	public void save(Dermatologist derm) {
		dermatologistRepository.save(derm);
		
	}
	
}
