package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacy;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	Dermatologist findOneById(Long id);
	Dermatologist findOneByfirstName(String name);
	
	@Query("from Pharmacy p join p.dermatologists d where d.id= :dermatologistId")
	List<Pharmacy> getDermPharmacies(Long dermatologistId);
}
