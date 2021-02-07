package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Errand;

public interface ErrandRepository extends JpaRepository<Errand, Long>{

	@Query("from Errand e join e.pharmacy ep where ep.id=:id")
	List<Errand> findAllByPharmacy(Long id);
}
