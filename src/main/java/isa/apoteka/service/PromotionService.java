package isa.apoteka.service;

import isa.apoteka.domain.Promotion;

public interface PromotionService {
	Promotion saveAndSendNotification(Promotion promotion);

	void subscribe(Long pharmacyId) throws Exception;

	void unsubscribe(Long pharmacyId);
}
