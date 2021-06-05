package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.apoteka.domain.EPrescription;

@Repository
public interface EPrescriptionRepository extends JpaRepository<EPrescription, Long> {
	EPrescription findByCode(String code);
}
