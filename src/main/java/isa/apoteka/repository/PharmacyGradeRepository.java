package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.DermatologistGrade;
import isa.apoteka.domain.PharmacyGrade;

public interface PharmacyGradeRepository extends JpaRepository<PharmacyGrade, Long> {
	
	@Query("Select AVG(g.grade) from  PharmacyGrade g join g.pharmacy d where d.id=:id")
	Double findAvgOfGrades(Long id);
	
	@Query("Select COUNT(c) from  PharmacyGrade c join c.pharmacy p where p.id=:id and c.grade=:grade")
	Long findNumberOfGrades(Long id, int grade);
}
