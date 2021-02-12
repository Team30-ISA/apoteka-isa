package isa.apoteka.repository;

import isa.apoteka.domain.DrugForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugFormRepository extends JpaRepository<DrugForm, Long> {
}
