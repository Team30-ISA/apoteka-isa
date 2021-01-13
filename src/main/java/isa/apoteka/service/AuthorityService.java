package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Authority;


public interface AuthorityService {
	List<Authority> findById(Long id);
	List<Authority> findByname(String name);
}
