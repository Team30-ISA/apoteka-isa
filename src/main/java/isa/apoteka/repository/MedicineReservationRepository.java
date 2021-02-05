package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.apoteka.domain.Promotion;
import isa.apoteka.domain.ReservedMedicine;

public interface MedicineReservationRepository extends JpaRepository<ReservedMedicine, Long>{

}
