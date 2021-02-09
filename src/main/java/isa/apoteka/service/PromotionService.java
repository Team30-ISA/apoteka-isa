package isa.apoteka.service;

import isa.apoteka.domain.Promotion;
import isa.apoteka.dto.PharmacyDTO;

import java.util.List;

public interface PromotionService {
	Promotion saveAndSendNotification(Promotion promotion);

	void subscribe(Long pharmacyId) throws Exception;

	void unsubscribe(Long pharmacyId);

	List<PharmacyDTO> findAllUserSubscribedPharmacies();
}
