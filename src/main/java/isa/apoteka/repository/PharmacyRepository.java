package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.MedicineDisplay;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long>{
	Pharmacy findOneByName(String name);
	
    @Query("from Dermatologist d join d.pharmacies p where p.id=:id")
	List<Dermatologist> findAllDermsWorkingInPharmacy(Long id);
    
    @Query("from Dermatologist d left join d.pharmacies p where ((not p.id=:id) or p.id=null) and d.id not in (select derm.id from Dermatologist as derm join derm.pharmacies pp where pp.id=:id)")
	List<Dermatologist> findAllDermsNotWorkingInPharmacy(Long id);
   
    @Query("from Pharmacist p join p.pharmacy pp where pp.id=:id")
	List<Pharmacist> findAllPharmsWorkingInPharmacy(Long id);
    
    @Query("from Dermatologist d join d.pharmacies p where d.firstName like %:firstName% and d.lastName like %:lastName% and  p.id=:id ")
	List<Dermatologist> searchDermsWorkingInPharmacy(Long id, String firstName, String lastName);
    
    @Query("from Pharmacist p join p.pharmacy pp where p.firstName like %:firstName% and p.lastName like %:lastName% and  pp.id=:id ")
   	List<Pharmacist> searchPharmsWorkingInPharmacy(Long id, String firstName, String lastName);
    
    @Query("from Medicine m join m.medicineInpharmacy mp where mp.pharmacy.id = ?1 and m.name = ?2")
   	Medicine searchMedicineInPharmacy(Long id, String name);
    
    @Query(value="select * from pharmacy", nativeQuery = true)
    List<Pharmacy> getAllPharmacies();
    
    @Modifying
    @Transactional
    @Query("update MedicineInPharmacy mp set mp.quantity = mp.quantity - ?3 where mp.medicine.id = ?2 and mp.pharmacy.id = ?1")
    void updateMedicineInPharmacy(Long pharmId, Long medId, int quantity);

    @Modifying
    @Transactional
    @Query("update Pharmacy p set p.name=:name, p.street=:address, p.city=:city, p.description=:description where p.id=:id")
	void update(Long id, String name, String address, String city, String description);
}
