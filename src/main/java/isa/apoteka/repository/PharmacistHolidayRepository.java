package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.LeaveRequestStatus;
import isa.apoteka.domain.PharmacistHoliday;

public interface PharmacistHolidayRepository extends JpaRepository<PharmacistHoliday, Long> {

	@Query("from PharmacistHoliday ph join ph.pharmacist p where p.id=:id and ph.status=:status")
	List<PharmacistHoliday> findAllByPharmId(Long id, LeaveRequestStatus status);
	
	
	@Query("from PharmacistHoliday ph join ph.pharmacist p join p.pharmacy pp where pp.id=:id")
	List<PharmacistHoliday> findAllByPharmacy(Long id);
	
	@Modifying
	@Query("update PharmacistHoliday p set p.status=1 where p.id=:id")
	void reject(Long id);

	@Modifying
	@Query("update PharmacistHoliday p set p.status=0 where p.id=:holidayId")
	void approve(Long holidayId);
}
