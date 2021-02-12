package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Notification;

public interface NotificationService {
	void noMedicineInStock(Date date, Long pharmacyId, String message);

	List<Notification> findAllMessages(Long id);
}
