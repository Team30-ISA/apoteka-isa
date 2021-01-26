package isa.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Authority;
import isa.apoteka.domain.Patient;
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
		Patient p = patientRepository.findById(id).orElseGet(null);
		return p;
	}

	public List<Patient> findAll() throws AccessDeniedException {
		List<Patient> result = patientRepository.findAll();
		return result;
	}

	@Override
	public Patient save(UserRequest userRequest) {
		/*
		User u = new User();
		u.setUsername(userRequest.getUsername());
		// pre nego sto postavimo lozinku u atribut hesiramo je
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		u.setFirstName(userRequest.getFirstname());
		u.setLastName(userRequest.getLastname());
		u.setEnabled(true);
		
		List<Authority> auth = authService.findByname("ROLE_USER");
		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		u.setAuthorities(auth);
		
		u = this.userRepository.save(u);
		*/
		return null;
	}
	
	public List<Patient> findAllPatients(){
		List<Patient> result = patientRepository.findAllPatients();
		return result;
	}

}
