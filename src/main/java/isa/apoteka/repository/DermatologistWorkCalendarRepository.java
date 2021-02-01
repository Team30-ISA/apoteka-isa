package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import isa.apoteka.domain.DermatologistWorkCalendar;

public interface DermatologistWorkCalendarRepository extends JpaRepository<DermatologistWorkCalendar, Long>{
	
	DermatologistWorkCalendar save(DermatologistWorkCalendar derm);
	
    @Query("from DermatologistWorkCalendar d join d.dermatologist dd where dd.id=:id")
	List<DermatologistWorkCalendar> findAllDermWorkCalendarByDermId(Long id);
    
    @Query("from DermatologistWorkCalendar d join d.dermatologist dd join d.pharmacy pp where dd.id=:dermatologistId and pp.id=:pharmacyId and d.startDate >= :start and d.endDate <= :end")
	List<DermatologistWorkCalendar> findAllDermWorkCalendarByDermIdAndPeriod(Long pharmacyId, Long dermatologistId, Date start, Date end);
    
    @Transactional
    @Modifying
    @Query(value="delete from Dermatologist_Work_Calendar d where d.start_Date >= :start and d.start_Date <= :end and d.dermatologist_id=:dermId", nativeQuery = true)
	void deleteDermWorkCalendarByDate(Date start, Date end, Long dermId);
}
