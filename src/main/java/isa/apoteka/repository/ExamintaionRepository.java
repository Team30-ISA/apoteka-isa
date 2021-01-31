package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Examination;

public interface ExamintaionRepository extends JpaRepository<Examination, Long> {

	@Query("from Examination e join e.pharmacistWorkCalendar p where p.pharmacist.id=:pharmacistId and e.startDate >= :start and e.startDate <= :end")
	List<Examination> findAllTerms(Long pharmacistId, Date start, Date end);
	
	@Query("select count(e) from Examination e join e.pharmacistWorkCalendar p where p.pharmacist.id=:pharmacistId and e.startDate >= :start and e.startDate <= :end")
	Long countTerms(Long pharmacistId, Date start, Date end);
}
