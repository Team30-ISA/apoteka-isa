package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import isa.apoteka.domain.MedicineInPharmacy;


public interface MedicineInPharmacyRepository extends JpaRepository<MedicineInPharmacy, Long>{

	@Modifying
	@Query(value = "insert into medicine_in_pharmacy (quantity, pharmacy_id, medicine_id) VALUES (0,:pharmacyId,:medicineId)", nativeQuery = true)
	void addNewMedicine(Long medicineId, Long pharmacyId);
	
	@Modifying
	@Query(value = "delete from medicine_in_pharmacy where pharmacy_id=:pharmacyId and medicine_id=:medicineId", nativeQuery = true)
	void deleteMedication(Long medicineId, Long pharmacyId);
	
	@Query("from MedicineInPharmacy m join m.pharmacy mp join m.medicine mm where mm.id=:medId and mp.id=:pharmacyId")
	MedicineInPharmacy getQuantityForMedicineInPharmacy(Long medId, Long pharmacyId);
	
	@Query("from MedicineInPharmacy m join m.pharmacy mp where mp.id=:pharmacyId")
	List<MedicineInPharmacy> getMedicineInPharmacy(Long pharmacyId);
	
	@Query("from MedicineInPharmacy m join m.pharmacy mp join m.medicine mm where mp.id=:pharmacyId and mm.name like %:name%")
	List<MedicineInPharmacy> searchMedicineInPharmacy(Long pharmacyId, String name);
}
