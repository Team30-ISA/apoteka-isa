package isa.apoteka.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.MedicinePrice;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicinePriceRepository extends JpaRepository<MedicinePrice, Long>{

	@Query("from MedicinePrice m join m.medicine mm join m.pharmacy mp where mp.id=:pharmacyId and mm.id=:medId  order by m.startOfPrice desc")
	List<MedicinePrice> findMedicinePrice(Long pharmacyId, Long medId);
	
	@Modifying
	@Query(value = "insert into medicine_price(end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (null ,null, null ,:medicineId,:pharmacyId)", nativeQuery = true)
	void addMedicinePrice(Long medicineId, Long pharmacyId);
	
	@Modifying
	@Query(value = "insert into medicine_price(end_of_price, price, start_of_price, medicine_id, pharmacy_id) values (:end ,:newPrice, :start ,:medicineId,:pharmacyId)", nativeQuery = true)
	void changePrice(Long medicineId, Long pharmacyId, int newPrice, Date start, Date end);

	@Query("from MedicinePrice m join m.medicine mm join m.pharmacy mp where mp.id=:pharmId and mm.id=:medId and m.startOfPrice >= :date and m.endOfPrice <= :date")
	MedicinePrice getPrice(Long medId, Long pharmId, Date date);

	List<MedicinePrice> findByMedicineIdIn(List<Long> collect);
}
