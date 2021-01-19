package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.User;
import isa.apoteka.dto.UserRequest;
import isa.apoteka.domain.VerificationToken;


public interface UserService {
    User findById(Long id);
    User findByEmail(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
	List<User> findAllDerms();
	List<User> findAllPharms();
	User findByToken(String verificationToken);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
}
