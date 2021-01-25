package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.apoteka.domain.Dermatologist;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	Dermatologist findOneById(Long id);
	Dermatologist findOneByfirstName(String name);
}
