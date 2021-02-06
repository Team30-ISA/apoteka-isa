package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Counseling;

public interface CounselingRepository extends JpaRepository<Counseling, Long>{
	
	@Query("from Counseling c join c.dermatologistWorkCalendar d where d.dermatologist.id=:dermatologistId and d.pharmacy.id=:pharmacyId and c.startDate >= :start and c.startDate <= :end")
	List<Counseling> findAllTerms(Long pharmacyId, Long dermatologistId, Date start, Date end);
	
	@Query("select count(c) from Counseling c join c.dermatologistWorkCalendar d where d.dermatologist.id=:dermatologistId and d.pharmacy.id=:pharmacyId and c.startDate >= :start and c.startDate <= :end")
	Long countTerms(Long pharmacyId, Long dermatologistId, Date start, Date end);
	
	@Query("select count(c) from Counseling c join c.dermatologistWorkCalendar d where d.dermatologist.id=:dermatologistId and c.startDate >= :start and c.startDate <= :end")
	Long countAllTerms(Long dermatologistId, Date start, Date end);
	
	@Query("from Counseling c join c.dermatologistWorkCalendar d join c.patient p where d.dermatologist.id=:dermatologistId and c.startDate >= :start order by c.startDate ASC")
	List<Counseling> findAllByDermAndStart(Long dermatologistId, Date start);
	
	@Modifying
    @Transactional
    @Query(value = "update counseling set patient_id = ?1 where id = ?2", nativeQuery = true)
    void update(Long patientId, Long counselingId);
	
	@Modifying
    @Transactional
    @Query(value = "update counseling set report = ?1 where id = ?2", nativeQuery = true)
    void updateReport(String report, Long counselingId);
	
}