package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import isa.apoteka.domain.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long>{
	
	@Query("from Pharmacist p where p.id= :pharmacistId")
	Pharmacist getPharmacist(Long pharmacistId);
}
