package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

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

	@Query("from ReservedMedicine r join r.pharmacy p where p.id=:id and date >= :startDate and date <= :endDate")
	List<ReservedMedicine> findFinishedReservationByPharmacy(Long id, Date startDate, Date endDate);

	@Query("from ReservedMedicine r join r.pharmacy p where p.id=:id")
	List<ReservedMedicine> findAllfinishedReservation(Long id);
}
