package isa.apoteka.service.impl;

import java.util.List;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Patient;
import isa.apoteka.dto.UserVerificationDTO;
import isa.apoteka.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Authority;
import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.repository.UserRepository;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityService authService;

	@Autowired
	private EmailService emailService;

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username);
		return u;
	}

	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElse(null);
		return u;
	}

	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}

	public User findUserByEmail (String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User save(UserRequest userRequest) {
		Patient patient = new Patient(userRequest);

		patient.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		
		List<Authority> auth = authService.findByname("ROLE_USER");
		patient.setAuthorities(auth);
		this.patientRepository.save(patient);

		emailService.sendVerificationEmail(patient, passwordEncoder.encode(patient.getEmail()));

		return patient;
	}
	
	public List<User> findAllDerms(){
		List<User> result = userRepository.findAllDerms();
		return result;
	}
	
	public List<User> findAllPharms(){
		List<User> result = userRepository.findAllPharms();
		return result;
	}

	public void verifyUser(UserVerificationDTO verificationData) throws Exception {
		Patient patient = patientRepository.getOne(verificationData.getUserId());

		if(!passwordEncoder.matches(patient.getEmail(), verificationData.getHash()))
			throw new Exception("Bad verification token");

		patient.setEnabled(true);
		patientRepository.save(patient);
	}
}
