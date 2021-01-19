package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import isa.apoteka.domain.Authority;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(String name);
}
