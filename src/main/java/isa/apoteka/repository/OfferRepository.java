package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Offer;
import isa.apoteka.domain.Patient;

public interface OfferRepository extends JpaRepository<Offer, Long> {

	@Query("from Offer o join o.errand e where e.id=:errandId")
	List<Offer> findAllOffersForErrand(Long errandId);
	
	@Modifying
	@Query("update Offer o set o.approved=:bool where o.id=:id")
	void offerApproval(Long id, Boolean bool);
}
