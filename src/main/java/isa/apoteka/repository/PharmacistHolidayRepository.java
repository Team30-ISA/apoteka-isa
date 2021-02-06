package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.PharmacistHoliday;

public interface PharmacistHolidayRepository extends JpaRepository<PharmacistHoliday, Long> {

	@Query("from PharmacistHoliday ph join ph.pharmacist p where p.id=:id and ph.approved=:approved")
	List<PharmacistHoliday> findAllByPharmId(Long id, Boolean approved);
}
