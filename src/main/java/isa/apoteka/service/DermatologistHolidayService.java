package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.dto.DermHolidayDTO;

public interface DermatologistHolidayService {
	void save(Dermatologist dermatologist, Date start, Date end);
	List<DermHolidayDTO> findAll();
	void reject(Long holidayId);
	void approve(Long holidayId, Long dermId, Date date, Date date2);
}
