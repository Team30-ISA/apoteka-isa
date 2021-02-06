package isa.apoteka.service;

import java.util.List;

import isa.apoteka.dto.SupplierDTO;

public interface OfferService {
	Boolean sendMail(Long errandId);
	List<SupplierDTO> findAllOffersForErrand(Long errandId);
	Boolean approveOffer(Long offerId, Long errandId);
}
