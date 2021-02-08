package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.dto.PharmHolidayDTO;

public interface PharmacistHolidayService {
	void save(Pharmacist pharmacist, Date start, Date end);
	Boolean isPharmOnHolidays(Long pharmacistId, Date start);
	List<PharmHolidayDTO> findAll(Long pharmacyId);
	void reject(Long holidayId);
	void approve(Long holidayId, Long pharmId, Date start, Date end);
}
