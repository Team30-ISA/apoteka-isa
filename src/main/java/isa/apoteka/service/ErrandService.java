package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Errand;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.ErrandPreviewDTO;

public interface ErrandService {

	Boolean save(Date deadline);
	List<Errand> findAllErrands();
	void delete(Long errandId, PharmacyAdmin admin);
	List<ErrandPreviewDTO> findAllValidErrands();

}
