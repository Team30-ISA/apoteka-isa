package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.PharmacistGrade;

public interface PharmacistGradeRepository extends JpaRepository<PharmacistGrade, Long>{

	@Query("Select AVG(p.grade) from PharmacistGrade p join p.pharmacist pp where pp.id=:id")
	Double findAvgOfGrades(Long id);
}
