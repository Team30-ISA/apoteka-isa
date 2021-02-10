package isa.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import isa.apoteka.service.OfferService;

@RestController
@RequestMapping(value = "api/offer")
public class OfferController {

	private OfferService offerService;
	
	@Autowired
	public OfferController(OfferService offerService) {
		this.offerService = offerService;
	}
	
	
	@GetMapping("/approveOffer")
	@PreAuthorize("hasRole('ADMIN')")	
	public Boolean approveOffer(Long offerId, Long errandId){
		offerService.approveOffer(offerId, errandId);
		return true;
	}
	
	@GetMapping("/sendEmail")
	@PreAuthorize("hasRole('ADMIN')")	
	public Boolean sendEmail(Long offerId, Long errandId){
		offerService.sendMail(errandId);
		return true;
	}
}
