package isa.apoteka.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Promotion;
import isa.apoteka.domain.User;
import isa.apoteka.dto.PromotionDTO;
import isa.apoteka.service.PromotionService;
import isa.apoteka.service.UserService;


@RestController
@RequestMapping(value = "api/promotion")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private EmailService emailService;
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<PromotionDTO> savePromotion(@RequestBody PromotionDTO promotionDTO) {

		Promotion promotion = new Promotion();
		promotion.setTitle(promotionDTO.getTitle());
		promotion.setContent(promotionDTO.getContent());
		promotion.setStartOfPromotion(promotionDTO.getStartOfPromotion());
		promotion.setEndOfPromotion(promotionDTO.getEndOfPromotion());

		promotion = promotionService.save(promotion);
		
		List<User> users = userService.findAll();
		for(User u : users) {
			try {
				System.out.println("Thread id: " + Thread.currentThread().getId());
				emailService.sendPromotionNotificaitionAsync(u,promotion);
			}catch( Exception e ){
				logger.info("Greska prilikom slanja emaila: " + e.getMessage());
			}
		}
		return new ResponseEntity<>(new PromotionDTO(promotion), HttpStatus.CREATED);
	}
	
		
	

}
