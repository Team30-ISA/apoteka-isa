package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long>{

	 	@Modifying
	    @Transactional
		@Query("delete from Pharmacist p where p.id=:pharmId")
		void firePharm(Long pharmId);
}
