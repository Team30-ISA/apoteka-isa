package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import org.springframework.stereotype.Repository;

@Repository
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
    @Query(value = "update counseling set patient_id = ?1 where id = ?2", nativeQuery = true)
    void update(Long patientId, Long counselingId);
	
	@Modifying
    @Query(value = "update counseling c set patient_id = ?1 where c.id = ?2", nativeQuery = true)
    void makeAppointment(Long patId, Long counsId);
	
	@Modifying
    @Query(value = "update counseling set report = ?1 where id = ?2", nativeQuery = true)
    void updateReport(String report, Long counselingId);
	
	@Modifying
    @Query(value = "update counseling c set patient_id = null where c.id = ?1", nativeQuery = true)
    void cancelAppointment(Long counsId);
	
	@Query("from Counseling c join c.dermatologistWorkCalendar d where d.dermatologist.id=:dermatologistId")
	List<Counseling> findAllByDermId(Long dermatologistId);
	
	@Query("from Counseling c join c.dermatologistWorkCalendar.pharmacy p where p.id=:pharmId")
	List<Counseling> findAllByPharmId(Long pharmId);
	
	@Query(value="select * from Counseling c", nativeQuery = true)
	List<Counseling> findAllByPatientId(Long patId);
	
	@Query("from Counseling c join c.dermatologistWorkCalendar.dermatologist d where c.id=:counsId")
	Dermatologist findDermatologistForCounseling(Long counsId);
	
	@Modifying
	@Query(value = "insert into counseling (start_date, duration, price, dermatologist_work_calendar_id, version) values (:start,:duration,:price,:dwcId, 0)", nativeQuery = true)
	void createCounseling(Date start, int duration, Float price, Long dwcId);

	@Query("from Counseling c join c.dermatologistWorkCalendar.pharmacy p where c.report != null and c.report != '' and c.startDate<= :kraj and c.startDate>= :pocetak and p.id=:id")
	List<Counseling> finishedCounseling(Long id, Date pocetak, Date kraj);

	@Query("from Counseling c join c.dermatologistWorkCalendar.pharmacy p where c.report != null and c.report != '' and p.id=:id")
	List<Counseling> allFinishedCounseling(Long id);

    List<Counseling> findByPatientId(Long id);
}
