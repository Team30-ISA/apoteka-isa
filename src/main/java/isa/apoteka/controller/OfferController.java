package isa.apoteka.controller;

import isa.apoteka.dto.OfferDTO;
import isa.apoteka.service.ErrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import isa.apoteka.service.OfferService;

@RestController
@RequestMapping(value = "api/offer")
public class OfferController {
	private OfferService offerService;
	private ErrandService errandService;

	@Autowired
	public OfferController(OfferService offerService, ErrandService errandService) {
		super();
		this.offerService = offerService;
		this.errandService = errandService;
	}

/*
	@Autowired
	public OfferController(OfferService offerService) {
		this.offerService = offerService;
	}
	*/
	
	@GetMapping("/approveOffer")
	@PreAuthorize("hasRole('ADMIN')")	
	public Boolean approveOffer(Long offerId, Long errandId){
		return offerService.approveOffer(offerId, errandId);
	}
	
	@GetMapping("/sendEmail")
	@PreAuthorize("hasRole('ADMIN')")	
	public Boolean sendEmail(Long offerId, Long errandId){
		return offerService.sendMail(errandId);
	}

	@PostMapping
	@PreAuthorize("hasRole('SUPL')")
	public ResponseEntity<?> createOffer(@RequestBody OfferDTO offerDTO){
		try {
			offerService.createOffer(offerDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/erands")
	@PreAuthorize("hasRole('SUPL')")
	public ResponseEntity<?> getAllErrands(){
		try {
			return new ResponseEntity<>(errandService.findAllValidErrands(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	@PreAuthorize("hasRole('SUPL')")
	public ResponseEntity<?> getMyOffers(){
		try {
			return new ResponseEntity<>(offerService.getSuplierOffers(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/stock")
	@PreAuthorize("hasRole('SUPL')")
	public ResponseEntity<?> getSupplierStock(){
		try {
			return new ResponseEntity<>(offerService.getSupplierStock(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
