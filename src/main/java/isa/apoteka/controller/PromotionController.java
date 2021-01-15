package isa.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Promotion;
import isa.apoteka.dto.PromotionDTO;
import isa.apoteka.service.PromotionService;


@RestController
@RequestMapping(value = "api/promotion")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<PromotionDTO> savePromotion(@RequestBody PromotionDTO promotionDTO) {

		Promotion promotion = new Promotion();
		promotion.setTitle(promotionDTO.getTitle());
		promotion.setContent(promotionDTO.getContent());
		promotion.setStartOfPromotion(promotionDTO.getStartOfPromotion());
		promotion.setEndOfPromotion(promotionDTO.getEndOfPromotion());

		promotion = promotionService.save(promotion);
		return new ResponseEntity<>(new PromotionDTO(promotion), HttpStatus.CREATED);
	}

}
