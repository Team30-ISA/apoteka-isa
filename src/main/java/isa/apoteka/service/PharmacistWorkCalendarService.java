package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.PharmacistWorkCalendar;
import isa.apoteka.dto.PeriodDTO;

public interface PharmacistWorkCalendarService {
	Boolean save(PharmacistWorkCalendar pharm);
	List<PharmacistWorkCalendar> findAllPharmWorkCalendarByPharmId(Long id);
	List<PeriodDTO> findAllPharmWorkCalendarByPharmIdAndPeriod(Long pharmacistId, Date start, Date end);
	void deletePharmWorkCalendarByDate(Date start);
}
