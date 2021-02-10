package isa.apoteka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import isa.apoteka.domain.Promotion;
import isa.apoteka.dto.PromotionDTO;
import isa.apoteka.service.PharmacyService;
import isa.apoteka.service.PromotionService;


@RestController
@RequestMapping(value = "api/promotion")
public class PromotionController {

	private PromotionService promotionService;	
	private PharmacyService pharmacyService;
	
	@Autowired 
	public PromotionController(PromotionService promotionService, PharmacyService pharmacyService) {
		this.promotionService = promotionService;
		this.pharmacyService = pharmacyService;
	}
	
	@PostMapping(value= "/save", consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> savePromotion(@RequestBody @Valid PromotionDTO promotionDTO) {

		if(promotionDTO.getStartOfPromotion().after(promotionDTO.getEndOfPromotion())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		Promotion promotion = new Promotion();
		promotion.setTitle(promotionDTO.getTitle());
		promotion.setContent(promotionDTO.getContent());
		promotion.setStartOfPromotion(promotionDTO.getStartOfPromotion());
		promotion.setEndOfPromotion(promotionDTO.getEndOfPromotion());
		promotion.setPharmacy(pharmacyService.findOne(promotionDTO.getPharmacyId()));		
		
		try {
			promotionService.saveAndSendNotification(promotion);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/subscribe")
	@PreAuthorize("hasRole('PATIENT')")	
	public Boolean subscribe(Long pharmacyId){
		promotionService.subscribe(pharmacyId);
		return true;
	}
	

}
