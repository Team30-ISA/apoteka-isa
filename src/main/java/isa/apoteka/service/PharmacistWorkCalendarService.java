package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacistWorkCalendar;
import isa.apoteka.dto.PeriodDTO;

public interface PharmacistWorkCalendarService {
	Boolean save(PharmacistWorkCalendar pwc, Pharmacist pharmacist) throws Exception;
	List<PharmacistWorkCalendar> findAllPharmWorkCalendarByPharmId(Long id);
	List<PeriodDTO> findAllPharmWorkCalendarByPharmIdAndPeriod(Long pharmacistId, Date start, Date end);
	PeriodDTO findPharmWorkCalendarByPharmIdAndDate(Long pharmacistId, Date start);
	void deletePharmWorkCalendarByDate(Date start, Long pharmId);
	void deletePharmWorkCalendarByPharm(Long id);
	PharmacistWorkCalendar findById(Long id);
}
