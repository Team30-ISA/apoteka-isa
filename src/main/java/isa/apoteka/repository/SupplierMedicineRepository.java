package isa.apoteka.repository;

import isa.apoteka.domain.SupplierMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierMedicineRepository extends JpaRepository<SupplierMedicine, Long> {
    List<SupplierMedicine> findAllBySupplierId(Long id);
}
