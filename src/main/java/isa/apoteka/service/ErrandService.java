package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Errand;
import isa.apoteka.dto.ErrandPreviewDTO;

public interface ErrandService {

	Long save(Date deadline);
	List<Errand> findAllErrands();

	List<ErrandPreviewDTO> findAllValidErrands();
}
