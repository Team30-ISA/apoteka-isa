package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Medicine;

public interface MedicineRepository  extends JpaRepository<Medicine, Long>  {
	
	@Query("from Medicine m where LOWER(m.name) like %:name%")
	List<Medicine> searchMedicinesByName(String name);
	
	@Query(value = "select count(p) from patient_allergies p where p.patient_id=:patientId and p.allergies_id=:medicineId", nativeQuery = true)
	Long isPatientAllergic(Long patientId, Long medicineId);
	
	@Query("select m.substitutes from Medicine m where m.id=:id")
	List<Medicine> getSubstitutesOfMedicine(Long id);
	
	@Query("select m.quantity from MedicineInPharmacy m where m.medicine.id=:medicineId and m.pharmacy.id=:pharmacyId")
	Integer getQuantityOfMedicineInPharmacy(Long medicineId, Long pharmacyId);
}
