package isa.apoteka.service.impl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import isa.apoteka.domain.*;
import isa.apoteka.dto.ComplaintsListsDTO;
import isa.apoteka.service.DermatologistService;
import isa.apoteka.service.ExaminationService;
import isa.apoteka.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import isa.apoteka.dto.PatientDTO;
import isa.apoteka.repository.LoyaltyProgramRepository;
import isa.apoteka.repository.PatientRepository;
import isa.apoteka.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;


	@Autowired
	private ExaminationService examinationService;

	@Autowired
	private PharmacyService pharmacyService;

	@Autowired
	private DermatologistService dermatologistService;
	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;

	@Override
	public Patient findByUsername(String username) throws UsernameNotFoundException {
		Patient p = patientRepository.findByUsername(username);
		return p;
	}

	public Patient findById(Long id) throws AccessDeniedException {
		Patient p = patientRepository.findById(id).orElse(null);
		return p;
	}
	
	@Override
	public List<Patient> findAll() throws AccessDeniedException {
		List<Patient> result = patientRepository.findAll();
		return result;
	}

	@Override
	public void update(PatientUpdateForm puf) {
		Patient p = findById(puf.getId());
		p.setFirstName(puf.getName());
		p.setLastName(puf.getSurname());
		p.setEmail(puf.getEmail());
		p.setPhonenumber(puf.getPhonenumber());
		
		this.patientRepository.update(p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhonenumber(), p.getId());
	}
	
	@Override
	public void updatePassword(PatientUpdateForm puf) {
		Patient p = findById(puf.getId());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		p.setPassword(passwordEncoder.encode(puf.getNewPass()));
		this.patientRepository.updatePassword(passwordEncoder.encode(puf.getNewPass()), p.getId());
	}
	
	@Override
	public List<Patient> findAllPatients(){
		List<Patient> result = patientRepository.findAllPatients();
		return result;
	}
	
	@Override
	public String getLogged() {
		return SecurityContextHolder.getContext().getAuthentication().toString();
	}

	@Override
	public List<Patient> findAllPatientsNotification(Long id) {
		List<Patient> result = patientRepository.findAllPatientsNotification(id);
		return result;
	}
	@Override
	public List<Medicine> searchReservedMedicineForPatient(Long id) {
		List<Medicine> result = patientRepository.searchReservedMedicineForPatient(id);
		List<Medicine> res = new ArrayList<Medicine>();
		for(Medicine medicine : result) {
			medicine.setPriceWithLoyalty(this.setPriceWithLoyaltyProgram(medicine.getPrice()));
			res.add(medicine);
		}
		return res;
	}
	@Override
	public void updateReservedMedicineForPatient(Long patId, Long medId, int quantity, Date date, String uid) {
		patientRepository.updateReservedMedicineForPatient(patId, medId, quantity, date, uid);
	}

	@Override
	public Boolean hasCounselings(Long patientId, Date start, Date end) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 2);
		Date endDate = calendar.getTime();
		List<Counseling> counselings = patientRepository.getPatientCounselings(patientId, startDate, endDate);
		System.out.println(start + " *** " + end);
		for(Counseling c : counselings) {
			calendar.setTime(c.getStartDate());
			calendar.add(Calendar.MINUTE, c.getDuration());
			endDate = calendar.getTime();
			System.out.println(c.getStartDate() + " XXX " + endDate);
			if((c.getStartDate().getTime() <= start.getTime() && endDate.getTime() > start.getTime())
					|| (c.getStartDate().getTime() < end.getTime() && endDate.getTime() >= end.getTime()) 
					|| (c.getStartDate().getTime() >= start.getTime() && endDate.getTime() <= end.getTime())) {
				System.out.println("true");
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean hasExaminations(Long patientId, Date start, Date end) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 2);
		Date endDate = calendar.getTime();
		List<Examination> examinations = patientRepository.getPatientExamintaions(patientId, startDate, endDate);
		for(Examination c : examinations) {
			calendar.setTime(c.getStartDate());
			calendar.add(Calendar.MINUTE, c.getDuration());
			endDate = calendar.getTime();
			if((c.getStartDate().getTime() <= start.getTime() && endDate.getTime() > start.getTime())
					|| (c.getStartDate().getTime() < end.getTime() && endDate.getTime() >= end.getTime()) 
					|| (c.getStartDate().getTime() >= start.getTime() && endDate.getTime() <= end.getTime())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<PatientDTO> findAllDTO() {
		return mapPatientListToPatientDTOList(patientRepository.findAll());
	}
	
	public List<PatientDTO> mapPatientListToPatientDTOList(List<Patient> patients) {
		List<PatientDTO> dtos = new ArrayList<PatientDTO>();
		
		for(Patient p : patients) {
			dtos.add(new PatientDTO(p));
		}
		
		return dtos;		
	}

	@Override
	public List<PatientDTO> findAllByName(String firstName, String lastName) {
		return mapPatientListToPatientDTOList(patientRepository.findAllByName(firstName, lastName));
	}

	@Override
	public ComplaintsListsDTO findAllEntitiesToComplain() {
		Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Pharmacist> pharmacistst =  examinationService.getAllExaminationPharmacistsByPatientId(p.getId());
		List<Dermatologist> dermatologists =  dermatologistService.getAllExaminationDermatologistsByPatientId(p.getId());
		List<Pharmacy> pharmacies = pharmacyService.getPharmaciesAsociatedWithUser(p.getId());

		pharmacistst = pharmacistst.stream().distinct().collect(Collectors.toList());
		dermatologists = dermatologists.stream().distinct().collect(Collectors.toList());
		pharmacies = pharmacies.stream().distinct().collect(Collectors.toList());

		ComplaintsListsDTO complaintsListsDTO = new ComplaintsListsDTO(pharmacistst, pharmacies, dermatologists);

		return complaintsListsDTO;
	}
	
	private double setPriceWithLoyaltyProgram(double hasMedicationsPrice) {
    	Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String status = patient.getLoyaltyCategory();
        double newPrice = 0;

        try {
            LoyaltyProgram loyaltyProgram = loyaltyProgramRepository.findAll().get(0);
            if(status.equals("REGULAR")) {
                newPrice = hasMedicationsPrice - hasMedicationsPrice * (loyaltyProgram.getRegularDiscount()/1);
            }
            else if(status.equals("SILVER")) {
                newPrice = hasMedicationsPrice - hasMedicationsPrice * (loyaltyProgram.getSilverDiscount()/1);
            }
            else if(status.equals("GOLD")) {
                newPrice = hasMedicationsPrice -hasMedicationsPrice * (loyaltyProgram.getGoldenDiscount()/1);
            }
        }
        catch(Exception e) {
            return hasMedicationsPrice;
        }

        return newPrice;
    }

}