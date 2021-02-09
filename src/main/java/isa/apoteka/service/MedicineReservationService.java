package isa.apoteka.service;

import isa.apoteka.domain.ReservedMedicine;
import isa.apoteka.dto.ReservedMedicineDTO;

public interface MedicineReservationService {
	ReservedMedicine SendNotification(ReservedMedicine reservedMedicine);
	ReservedMedicineDTO findReservationByPharmacy(String uid, Long pharmacyId);
	void approveReservation(String uid, Long pharmacyId);
}
