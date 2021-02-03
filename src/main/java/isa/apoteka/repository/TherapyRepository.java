package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Therapy;

public interface TherapyRepository extends JpaRepository<Therapy, Long>   {
	
}
