package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.DermatologistGrade;

public interface DermatologistGradeRepository extends JpaRepository<DermatologistGrade, Long>{
	
	
	@Query("Select AVG(g.grade) from DermatologistGrade g join g.dermatologist d where d.id=:id")
	Double findAvgOfGrades(Long id);
}
