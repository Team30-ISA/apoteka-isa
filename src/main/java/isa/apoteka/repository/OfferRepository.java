package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import isa.apoteka.domain.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

	@Query("from Offer o join o.errand e where e.id=:errandId")
	List<Offer> findAllOffersForErrand(Long errandId);
	
	@Modifying
	@Query(value = "UPDATE offer SET is_approved = NOT COALESCE( is_approved, 't' ) WHERE id=:id", nativeQuery = true)
	void offerApproval(Long id);
	
	@Modifying
	@Query(value = "UPDATE errand SET finished = NOT COALESCE( finished, 't' ) WHERE id=:id", nativeQuery = true)
	void finishErrand(Long id);

    Offer findByErrandIdAndSupplierId(Long id, Long id1);

	List<Offer> findAllBySupplierId(Long id);
}
      