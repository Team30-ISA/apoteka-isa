package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;


public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
	List<User> findAllDerms();
	List<User> findAllPharms();
}
