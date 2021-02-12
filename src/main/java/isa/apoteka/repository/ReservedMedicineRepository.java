package isa.apoteka.repository;

import isa.apoteka.domain.ReservedMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservedMedicineRepository extends JpaRepository<ReservedMedicine, Long> {
    List<ReservedMedicine> findByPatientId(Long id);
}
