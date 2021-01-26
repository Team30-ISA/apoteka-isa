package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.User;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PatientUpdateForm;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername( String username );
    
    @Query("from User u join u.authorities a where a.id=10")
	List<Patient> findAllPatients();
    
    @Modifying
    @Transactional
    @Query("update Patient p set p.firstName = ?1, p.lastName = ?2 where p.id = ?3")
    void update(String firstName, String lastName, Long id);
    
    @Modifying
    @Transactional
    @Query("update Patient p set p.password = ?1 where p.id = ?2")
    void updatePassword(String password, Long id);
}
