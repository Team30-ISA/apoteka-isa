package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacist;

public interface PharmacistRepository extends JpaRepository<Pharmacist, Long>{

	 	@Modifying
		@Query("delete from Pharmacist p where p.id=:pharmId")
		void firePharm(Long pharmId);

		Pharmacist findByUsername(String username);
		
		@Modifying
	    @Transactional
	    @Query("update Pharmacist p set p.firstName = ?1, p.lastName = ?2 where p.id = ?3")
	    void update(String firstName, String lastName, Long id);
		
		@Query("from Pharmacist p where p.id= :pharmacistId")
		Pharmacist getPharmacist(Long pharmacistId);
		
	    @Query("from Pharmacist p join p.pharmacy pp where p.firstName like %:firstName% and p.lastName like %:lastName% and pp.id=:pharmacyId")
	   	List<Pharmacist> searchPharmsForAdmin(String firstName, String lastName, Long pharmacyId);
	    
	    @Query("from Pharmacist p join p.pharmacy pp where p.id=:id and pp.id in :pharmacy")
		List<Pharmacist> worksInPharmacy(Long id, List<Long> pharmacy);
	    
	    @Query("from Pharmacist p where p.firstName like %:firstName% and p.lastName like %:lastName%")
		List<Pharmacist> searchPharms(String firstName, String lastName);
}

