package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Patient;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	
	Medicine getById(Long medId);
}
