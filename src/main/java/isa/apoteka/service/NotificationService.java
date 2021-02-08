package isa.apoteka.service;

import java.util.Date;

public interface NotificationService {
	void noMedicineInStock(Date date, Long pharmacyId, String message);
}
