package isa.apoteka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
	

    @GetMapping
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> findAllUserSubscribedPharmacies() {
        try {
            return new ResponseEntity<>(promotionService.findAllUserSubscribedPharmacies(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/subscribe")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> subscribe(@RequestBody Long pharmacyId) {
        try {
            promotionService.subscribe(pharmacyId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/unsubscribe")
    @PreAuthorize("hasRole('PATIENT')")
    public Boolean usubscribe(@RequestBody Long pharmacyId) {
        promotionService.unsubscribe(pharmacyId);
        return true;
    }
}
