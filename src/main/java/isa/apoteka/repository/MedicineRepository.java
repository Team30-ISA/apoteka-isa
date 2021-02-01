package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.apoteka.domain.Medicine;

public interface MedicineRepository  extends JpaRepository<Medicine, Long>  {
}
