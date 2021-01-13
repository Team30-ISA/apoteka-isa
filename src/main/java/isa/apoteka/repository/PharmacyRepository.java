package isa.apoteka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long>{
	Pharmacy findOneByName(String name);
	
    @Query("from Dermatologist d join d.pharmacies p where p.id=:id")
	List<Dermatologist> findAllDermsWorkingInPharmacy(Long id);
    
    @Query("from Pharmacist p join p.pharmacy pp where pp.id=:id")
	List<Pharmacist> findAllPharmsWorkingInPharmacy(Long id);
}
