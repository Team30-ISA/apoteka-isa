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
	@Query(value = "insert into pharmacy_dermatologist (user_id, pharmacy_id) values (:dermId,:pharmacyId)", nativeQuery = true)
	void hireDerm(Long dermId, Long pharmacyId);
    
    @Modifying
	@Query(value = "delete from pharmacy_dermatologist where user_id=:dermId and pharmacy_id=:pharmacyId", nativeQuery = true)
	void fireDerm(Long dermId, Long pharmacyId);

	@Query("from Pharmacy p join p.dermatologists d where d.id= :dermatologistId")
	List<Pharmacy> getDermPharmacies(Long dermatologistId);
	
	@Modifying
    @Query("update Dermatologist p set p.firstName = ?1, p.lastName = ?2 where p.id = ?3")
    void update(String firstName, String lastName, Long id);
	
    @Query("from Dermatologist d where d.firstName like %:firstName% and d.lastName like %:lastName%")
	List<Dermatologist> searchDerms(String firstName, String lastName);
    
    @Query("from Dermatologist d join d.pharmacies dp where d.firstName like %:firstName% and d.lastName like %:lastName% and dp.id=:pharmacyId")
   	List<Dermatologist> searchDermsForAdmin(String firstName, String lastName, Long pharmacyId);
    
    @Query("from Dermatologist d join d.pharmacies dp where d.id=:id and dp.id in :pharmacy")
	List<Dermatologist> worksInPharmacy(Long id, List<Long> pharmacy);

}
