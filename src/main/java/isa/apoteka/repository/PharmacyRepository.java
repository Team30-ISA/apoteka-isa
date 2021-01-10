package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.apoteka.domain.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long>{

}
