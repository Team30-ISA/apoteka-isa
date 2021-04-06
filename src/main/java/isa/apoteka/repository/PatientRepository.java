package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Examination;
import isa.apoteka.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByUsername( String username );
    
    @Query("from User u join u.authorities a where a.id=10")
	List<Patient> findAllPatients();
    
    @Modifying
    @Transactional
    @Query("update Patient p set p.firstName = ?1, p.lastName = ?2, p.email = ?3 where p.id = ?4")
    void update(String firstName, String lastName, String email, Long id);
    
    @Modifying
    @Transactional
    @Query("update Patient p set p.password = ?1 where p.id = ?2")
    void updatePassword(String password, Long id);
    
    @Query("from Patient p join p.pharmacies pp where pp.id=:id")
	List<Patient> findAllPatientsNotification(Long id);
    
    @Query("from Medicine m join m.reservedMedicine rm where rm.patient.id = ?1")
   	List<Medicine> searchReservedMedicineForPatient(Long id);
    
    @Modifying
    @Transactional
    @Query(value="insert into Reserved_Medicine (quantity, date, medicine_id, patient_id, uid) values(?3, ?4, ?2, ?1, ?5)", nativeQuery=true)
    void updateReservedMedicineForPatient(Long patId, Long medId, int quantity, Date date, String uid);
    
    @Query("from Counseling c join c.patient p where p.id=:patientId and c.startDate >= :start  and c.startDate <= :end")
	List<Counseling> getPatientCounselings(Long patientId, Date start,  Date end);
    
    @Query("from Examination e join e.patient p where p.id=:patientId and e.startDate >= :start  and e.startDate <= :end")
	List<Examination> getPatientExamintaions(Long patientId, Date start,  Date end);
    
    @Query("from Patient p where LOWER(p.firstName) like %:firstName% and LOWER(p.lastName) like %:lastName%")
	List<Patient> findAllByName(String firstName, String lastName);
    
    
    
}
