package isa.apoteka.service;

import java.util.List;

import isa.apoteka.dto.MedicineForSupplyDTO;
import isa.apoteka.dto.MedicineQuantityDTO;

public interface MedicineQuantityService {
	Boolean insert(List<MedicineQuantityDTO> dto);
	List<MedicineForSupplyDTO> getMedicineForErrand(Long errandId);
}
