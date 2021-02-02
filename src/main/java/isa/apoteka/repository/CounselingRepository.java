package isa.apoteka.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import isa.apoteka.domain.Counseling;

public interface CounselingRepository extends JpaRepository<Counseling, Long>{
	
	@Query("from Counseling c join c.dermatologistWorkCalendar d where d.dermatologist.id=:dermatologistId and d.pharmacy.id=:pharmacyId and c.startDate >= :start and c.startDate <= :end")
	List<Counseling> findAllTerms(Long pharmacyId, Long dermatologistId, Date start, Date end);
	
	@Query("select count(c) from Counseling c join c.dermatologistWorkCalendar d where d.dermatologist.id=:dermatologistId and d.pharmacy.id=:pharmacyId and c.startDate >= :start and c.startDate <= :end")
	Long countTerms(Long pharmacyId, Long dermatologistId, Date start, Date end);	
}