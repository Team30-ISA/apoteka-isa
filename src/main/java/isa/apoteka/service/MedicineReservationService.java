package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.ReservedMedicine;
import isa.apoteka.dto.ReservedMedicineDTO;

public interface MedicineReservationService {
	ReservedMedicine SendNotification(ReservedMedicine reservedMedicine);
	ReservedMedicineDTO findReservationByPharmacy(String uid, Long pharmacyId);
	void approveReservation(String uid, Long pharmacyId);
	List<ReservedMedicine> findFinishedReservationByPharmacy(Long id, Date startDate, Date endDate);
	List<ReservedMedicine> allfinishedReservation(Long id);
	List<ReservedMedicine> findReservationForMedicineAndPharmacyNotFinished(Long medicineId, Long pharmacyId);
}
