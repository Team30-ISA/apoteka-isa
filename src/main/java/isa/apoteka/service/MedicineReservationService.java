package isa.apoteka.service;

import isa.apoteka.domain.Promotion;
import isa.apoteka.domain.ReservedMedicine;

public interface MedicineReservationService {
	ReservedMedicine SendNotification(ReservedMedicine reservedMedicine);
}
