package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Offer;
import isa.apoteka.dto.SupplierDTO;
import isa.apoteka.repository.OfferRepository;
import isa.apoteka.service.OfferService;

@Service
@Transactional(readOnly = true)
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
			dtos.add(new SupplierDTO(o.getSupplier().getId(),o.getSupplier().getFirstName(),o.getSupplier().getLastName(),o.getPrice(),o.getSupplyDeadline()));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean approveOffer(Long id, Long errandId) {
		List<Offer> offers =  offerRepository.findAllOffersForErrand(errandId);
		for(Offer o : offers) {
			if(o.getId() == id) {
				offerRepository.offerApproval(id, true);
			}else {
				offerRepository.offerApproval(id, false);
			}
		}
		
		return sendMail(errandId);
		
	}
	
	public Boolean sendMail(Long errandId) {
		List<Offer> offers =  offerRepository.findAllOffersForErrand(errandId);
		for(Offer o : offers) {
			emailService.sendOfferNotificaitionAsync(o);
		}
		
		return true;
	}

}
