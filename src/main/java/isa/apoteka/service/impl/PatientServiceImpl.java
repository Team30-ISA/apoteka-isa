package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Authority;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PatientUpdateForm;
import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.repository.PatientRepository;
import isa.apoteka.repository.UserRepository;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.PatientService;
import isa.apoteka.service.UserService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityService authService;

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
		
		this.patientRepository.update(p.getFirstName(), p.getLastName(), p.getId());
	}
	
	@Override
	public void updatePassword(PatientUpdateForm puf) {
		Patient p = findById(puf.getId());
		p.setPassword(passwordEncoder.encode(puf.getNewpass()));
		
		this.patientRepository.updatePassword(passwordEncoder.encode(puf.getNewpass()), p.getId());
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

}
