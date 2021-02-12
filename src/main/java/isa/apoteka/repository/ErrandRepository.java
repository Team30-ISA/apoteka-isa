package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Errand;

public interface ErrandRepository extends JpaRepository<Errand, Long>{

	@Query("from Errand e join e.pharmacy ep where ep.id=:id")
	List<Errand> findAllByPharmacy(Long id);

	@Modifying
	@Query(value="insert into errand (creation_time, deadline, pharmacy_id, finished, admin_id) values (:date,:startDate,:id, :b, :id2)", nativeQuery = true)
	void saveNewErrand(boolean b, Date date, Date startDate, Long id, Long id2);
}
