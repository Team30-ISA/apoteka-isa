package isa.apoteka.repository;

import isa.apoteka.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Promotion;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long>{

	@Query(value="insert into promotion_notification (patient_id, pharmacy_id) values (:id,:pharmacyId)", nativeQuery=true)
	void newSubscription(Long id, Long pharmacyId);
}
