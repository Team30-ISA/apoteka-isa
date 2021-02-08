package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.DermatologistHoliday;

public interface DermatologistHolidayRepository extends JpaRepository<DermatologistHoliday, Long> {

	@Modifying
	@Query("update DermatologistHoliday p set p.status=1 where p.id=:holidayId")
	void reject(Long holidayId);
	
	@Modifying
	@Query("update DermatologistHoliday p set p.status=0 where p.id=:holidayId")
	void approve(Long holidayId);

}
