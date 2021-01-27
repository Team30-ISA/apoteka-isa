package isa.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import isa.apoteka.domain.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long>{
	Promotion save(Promotion promotion);
}
