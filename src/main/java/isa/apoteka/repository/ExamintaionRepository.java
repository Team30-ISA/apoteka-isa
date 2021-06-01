package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Examination;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamintaionRepository extends JpaRepository<Examination, Long> {

	@Query("from Examination e join e.pharmacistWorkCalendar p where p.pharmacist.id=:pharmacistId and e.startDate >= :start and e.startDate <= :end")
	List<Examination> findAllTerms(Long pharmacistId, Date start, Date end);
	
	@Query("select count(e) from Examination e join e.pharmacistWorkCalendar p where p.pharmacist.id=:pharmacistId and e.startDate >= :start and e.startDate <= :end")
	Long countTerms(Long pharmacistId, Date start, Date end);
	
	@Query("from Examination e join e.pharmacistWorkCalendar p join e.patient pp where p.pharmacist.id=:pharmacistId and e.startDate >= :start order by e.startDate ASC")
	List<Examination> findAllByPharmAndStart(Long pharmacistId, Date start);
	
	@Modifying
    @Query(value = "update examination set report = ?1 where id = ?2", nativeQuery = true)
    void updateReport(String report, Long examinationId);
	
	@Modifying
	@Query(value = "insert into examination (start_date, duration, price, pharmacist_work_calendar_id, patient_id) values (:start,:duration,0,:pwcId,:patientId)", nativeQuery = true)
	void createExamination(Date start, int duration, Long patientId, Long pwcId);
	
	@Query("from Examination e join e.pharmacistWorkCalendar p where p.pharmacist.id=:pharmacistId")
	List<Examination> findAllByPharmId(Long pharmacistId);
	
	@Query("from Examination e join e.patient p where p.id=:patId")
	List<Examination> getExaminationsForPatient(Long patId);
	
	@Modifying
    @Query(value = "update examination e set patient_id = null where e.id = ?1", nativeQuery = true)
    void cancelAppointment(Long examId);

	@Query("from Examination e join e.pharmacistWorkCalendar.pharmacy p where e.report != null and e.report != '' and e.startDate<= :endDate and e.startDate>= :startDate and p.id=:pharmacyId")
	List<Examination> finishedExaminations(Long pharmacyId, Date startDate, Date endDate);

    List<Examination> findByPatientId(Long id);
}
