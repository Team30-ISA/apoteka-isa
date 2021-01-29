package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.DermatologistWorkCalendar;
import isa.apoteka.dto.PeriodDTO;

public interface DermatologistWorkCalendarService {
	Boolean save(DermatologistWorkCalendar derm);
	List<DermatologistWorkCalendar> findAllDermWorkCalendarByDermId(Long id);
	List<PeriodDTO> findAllDermWorkCalendarByDermIdAndPeriod(Long pharmacyId, Long dermatologistId, Date start, Date end);
	void deleteDermWorkCalendarByDate(Date start);
}
