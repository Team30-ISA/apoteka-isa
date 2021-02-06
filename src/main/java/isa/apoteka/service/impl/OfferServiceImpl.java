package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Offer;
import isa.apoteka.dto.SupplierDTO;
import isa.apoteka.repository.OfferRepository;
import isa.apoteka.service.OfferService;

@Service
//@Transactional(readOnly = true)
public class OfferServiceImpl implements OfferService{
	
	private OfferRepository offerRepository;
	private EmailService emailService;
	@Autowired
	public OfferServiceImpl(OfferRepository offerRepository, EmailService emailService) {
		this.offerRepository = offerRepository;
		this.emailService = emailService;
	}   

	@Override
	public List<SupplierDTO> findAllOffersForErrand(Long errandId) {
		List<Offer> offers = offerRepository.findAllOffersForErrand(errandId);
		List<SupplierDTO> dtos = new ArrayList<SupplierDTO>();		
		for(Offer o : offers) {
			dtos.add(new SupplierDTO(o.getSupplier().getId(),o.getId(),o.getSupplier().getFirstName(),o.getSupplier().getLastName(),o.getPrice(),o.getSupplyDeadline(), o.getIsApproved()));
		}
		
		return dtos;
	}

	@Override
	public Boolean approveOffer(Long id, Long errandId) {
		offerRepository.finishErrand(errandId);
		List<Offer> offers =  offerRepository.findAllOffersForErrand(errandId);
		System.out.println("*******************");
		System.out.println(offers.size());
		for(Offer o : offers) {
			if(o.getId().equals(id)) {
				offerRepository.offerApproval(id);
			}
		}
		return true;
	}
	
	@Override
	public Boolean sendMail(Long errandId) {
		List<Offer> offers1 =  offerRepository.findAllOffersForErrand(errandId);
		System.out.println("*******************");
		System.out.println(offers1.size());
		for(Offer o : offers1) {
			System.out.println(o);
			System.out.println(o.getIsApproved());
			emailService.sendOfferNotificaitionAsync(o);
		}
		
		return true;  
	}

}
