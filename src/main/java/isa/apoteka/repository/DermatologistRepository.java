package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacy;

public interface DermatologistRepository extends JpaRepository<Dermatologist, Long>{
	
	Dermatologist findOneById(Long id);
	
	Dermatologist findOneByfirstName(String name);
	

    @Modifying
    @Transactional
	@Query(value = "insert into pharmacy_dermatologist (user_id, pharmacy_id) values (:dermId,:pharmacyId)", nativeQuery = true)
	void hireDerm(Long dermId, Long pharmacyId);
    
    @Modifying
    @Transactional
	@Query(value = "delete from pharmacy_dermatologist where user_id=:dermId and pharmacy_id=:pharmacyId", nativeQuery = true)
	void fireDerm(Long dermId, Long pharmacyId);

	@Query("from Pharmacy p join p.dermatologists d where d.id= :dermatologistId")
	List<Pharmacy> getDermPharmacies(Long dermatologistId);
	
	@Modifying
    @Transactional
    @Query("update Dermatologist p set p.firstName = ?1, p.lastName = ?2 where p.id = ?3")
    void update(String firstName, String lastName, Long id);

}
