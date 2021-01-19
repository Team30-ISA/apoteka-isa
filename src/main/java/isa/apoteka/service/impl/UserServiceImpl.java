package isa.apoteka.service.impl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Authority;
import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;
import isa.apoteka.domain.User;
import isa.apoteka.dto.UserRequest;
import isa.apoteka.domain.VerificationToken;
import isa.apoteka.repository.UserRepository;
import isa.apoteka.repository.VerificationTokenRepository;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityService authService;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;

	@Override
	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	@Override
	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}

	@Override
	public User save(UserRequest userRequest) {
		User u = new User();
		u.setEmail(userRequest.getEmail());
		// pre nego sto postavimo lozinku u atribut hesiramo je
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		u.setFirstName(userRequest.getFirstName());
		u.setLastName(userRequest.getLastName());
		u.setEnabled(false);
		u.setTelephone(userRequest.getTelephoneNumber());
		/*u.setCountry(new Country(userRequest.getCountryId(), userRequest.getCountryName()));
		u.setCity(new City(userRequest.getCityId(), userRequest.getCityName()));
		u.setAddress(new Address(userRequest.getAddressId(), userRequest.getStreet(), userRequest.getStreetNumber()));
		*/
		u.setAuthorities(findAllAuthorities());
		
		u = this.userRepository.save(u);
		return u;
	}
	
	private List<Authority> findAllAuthorities(){
		Authority authority = new Authority();
		String roleName = authority.getName();
		
		List<Authority> auth = authService.findByname("ROLE_USER");
		
		if(roleName.equals("ROLE_PHARM_ADMIN")) {
			auth = authService.findByname("ROLE_PHARM_ADMIN");
		}else if(roleName.equals("ROLE_SYSTEM_ADMIN")) {
			auth = authService.findByname("ROLE_SYSTEM_ADMIN");
		}else if(roleName.equals("ROLE_DERM")) {
			auth = authService.findByname("ROLE_DERM");
		}else if(roleName.equals("ROLE_PHARM")) {
			auth = authService.findByname("ROLE_PHARM");
		}else if(roleName.equals("ROLE_PATIENT")) {
			auth = authService.findByname("ROLE_PATIENT");
		}else {
			auth = authService.findByname("ROLE_SUPPLIER");
		}
		
		return auth;
	}
	
	@Override
	public List<User> findAllDerms(){
		List<User> result = userRepository.findAllDerms();
		return result;
	}
	
	@Override
	public List<User> findAllPharms(){
		List<User> result = userRepository.findAllPharms();
		return result;
	}

	@Override
	public User findByEmail(String email) throws UsernameNotFoundException{
		User u = userRepository.findByEmail(email);
		return u;
	}

	@Override
    public User findByToken(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

}
