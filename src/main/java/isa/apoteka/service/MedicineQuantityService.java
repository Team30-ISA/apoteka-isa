package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.MedicineForSupplyDTO;
import isa.apoteka.dto.MedicineQuantityDTO;

public interface MedicineQuantityService {
	Boolean insert(List<MedicineQuantityDTO> dto);
	List<MedicineForSupplyDTO> getMedicineForErrand(Long errandId);
	Boolean changeQuantity(Long errandId);
	void deleteMedQuantity(Long errandId, PharmacyAdmin admin);
}
