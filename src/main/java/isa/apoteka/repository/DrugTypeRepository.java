package isa.apoteka.repository;

import isa.apoteka.domain.DrugType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugTypeRepository extends JpaRepository<DrugType, Long> {
}
