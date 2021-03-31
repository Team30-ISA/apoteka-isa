package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import isa.apoteka.domain.MedicineEPrescription;

public interface MedicineEPrescriptionRepository extends JpaRepository<MedicineEPrescription, Long> {

}
