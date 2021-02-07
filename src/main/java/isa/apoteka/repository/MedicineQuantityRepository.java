package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.MedicineQuantity;

public interface MedicineQuantityRepository extends JpaRepository<MedicineQuantity, Long>{
	
	@Modifying
	@Query(value="insert into medicine_quantity(medicine_id, errand_id, quantity) values (:medId ,:errandId, :quantity)", nativeQuery = true)
	void insertNew(Long medId,Long errandId,int quantity);
	
	@Query(value="from MedicineQuantity mq join mq.errand mqe where mqe.id=:errandId")
	List<MedicineQuantity> getMedicineForErrand(Long errandId);
}
