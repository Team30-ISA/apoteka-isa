package isa.apoteka.repository;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.DermatologistWorkCalendar;
import isa.apoteka.domain.PharmacistWorkCalendar;

public interface PharmacistWorkCalendarRepository extends JpaRepository<PharmacistWorkCalendar, Long>{
	
	PharmacistWorkCalendar save(PharmacistWorkCalendar pharm);
	
    @Query("from PharmacistWorkCalendar p join p.pharmacist pp where pp.id=:id")
	List<PharmacistWorkCalendar> findAllPharmWorkCalendarByPharmId(Long id);
    
    @Query("from PharmacistWorkCalendar p join p.pharmacist pp where pp.id=:pharmacistId and p.startDate >= :start and p.endDate <= :end")
	List<PharmacistWorkCalendar> findAllPharmWorkCalendarByPharmIdAndPeriod(Long pharmacistId, Date start, Date end);
    
    @Transactional
    @Modifying
    @Query(value="delete from Pharmacist_Work_Calendar p where p.start_Date >= :start and p.start_Date <= :end and p.pharmacist_id=:pharmId", nativeQuery = true)
	void deletePharmWorkCalendarByDate(Date start, Date end, Long pharmId);
    
    @Transactional
    @Modifying
    @Query(value="delete from Pharmacist_Work_Calendar p where p.pharmacist_id=:id", nativeQuery = true)
	void deletePharmWorkCalendarByPharm(Long id);
    
    @Query("from PharmacistWorkCalendar p join p.pharmacist pp where pp.id=:pharmacistId and p.startDate >= :start and p.startDate <= :end")
    PharmacistWorkCalendar findPharmWorkCalendarByPharmIdAndDate(Long pharmacistId, Date start, Date end);
}
