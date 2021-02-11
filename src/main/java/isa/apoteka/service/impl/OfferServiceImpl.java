package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Errand;
import isa.apoteka.domain.Offer;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.SupplierDTO;
import isa.apoteka.repository.ErrandRepository;
import isa.apoteka.repository.OfferRepository;
import isa.apoteka.service.ErrandService;
import isa.apoteka.service.MedicineQuantityService;
import isa.apoteka.service.OfferService;

@Service
@Transactional(readOnly = true)
public class OfferServiceImpl implements OfferService{
	
	private OfferRepository offerRepository;
	private EmailService emailService;
	private ErrandRepository errandRepository;
	private MedicineQuantityService medQuantityService;
	
	@Autowired
	public OfferServiceImpl(OfferRepository offerRepository, EmailService emailService, ErrandRepository errandRepository, MedicineQuantityService medQuantityService) {
		this.offerRepository = offerRepository;
		this.emailService = emailService;
		this.errandRepository = errandRepository;
		this.medQuantityService = medQuantityService;
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
	@Transactional(readOnly = false)
	public Boolean approveOffer(Long offerid, Long errandId) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Errand errand = errandRepository.findById(errandId).orElse(null);
		if(errand == null) {
			return false;
		}else if(!errand.getAdmin().getId().equals(admin.getId())) {
			return false;
		}
		  
		System.out.println(errandId);
		offerRepository.finishErrand(errandId);
		medQuantityService.changeQuantity(errandId);
		List<Offer> offers =  offerRepository.findAllOffersForErrand(errandId);
		for(Offer o : offers) {
			if(o.getId().equals(offerid)) {
				offerRepository.offerApproval(offerid);
			}
		}
		return true;
	}
	
	@Override
	public Boolean sendMail(Long errandId) {
		List<Offer> offers1 =  offerRepository.findAllOffersForErrand(errandId);
		for(Offer o : offers1) {
			System.out.println(o);
			System.out.println(o.getIsApproved());
			emailService.sendOfferNotificaitionAsync(o);
		}
		
		return true;  
	}

}
