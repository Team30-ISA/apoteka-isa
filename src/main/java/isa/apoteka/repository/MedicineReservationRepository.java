package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.ReservedMedicine;

public interface MedicineReservationRepository extends JpaRepository<ReservedMedicine, Long>{

	@Query("from ReservedMedicine r join r.pharmacy p where r.uid=:uid and p.id=:pharmacyId")
	ReservedMedicine findReservationByPharmacy(String uid, Long pharmacyId);
	
	@Modifying
	@Query("update ReservedMedicine set approved=true where uid=:uid")
	void approveReservation(String uid);
}
