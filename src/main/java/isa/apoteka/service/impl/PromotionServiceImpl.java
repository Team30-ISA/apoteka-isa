package isa.apoteka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Promotion;
import isa.apoteka.repository.PromotionRepository;
import isa.apoteka.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	public Promotion save(Promotion promotion) {
		return promotionRepository.save(promotion);
	}

}
