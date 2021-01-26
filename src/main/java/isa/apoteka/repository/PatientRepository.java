package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.User;
import isa.apoteka.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername( String username );
    
    @Query("from User u join u.authorities a where a.id=10")
	List<Patient> findAllPatients();
    
}
