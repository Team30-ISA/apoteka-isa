package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.Offer;
import isa.apoteka.dto.MedicineQuantityPreviewDTO;
import isa.apoteka.dto.OfferDTO;
import isa.apoteka.dto.OfferPreviewDTO;
import isa.apoteka.dto.SupplierDTO;

public interface OfferService {
	Boolean sendMail(Long errandId);
	List<SupplierDTO> findAllOffersForErrand(Long errandId);
	Boolean approveOffer(Long offerId, Long errandId);

    void createOffer(OfferDTO offerDTO) throws Exception;

	List<OfferPreviewDTO> getSuplierOffers();

	List<MedicineQuantityPreviewDTO> getSupplierStock();
}
