package isa.apoteka.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import isa.apoteka.domain.Errand;

public interface ErrandRepository extends JpaRepository<Errand, Long>{

}
