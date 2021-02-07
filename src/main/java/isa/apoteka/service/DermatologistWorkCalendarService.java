package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.DermatologistWorkCalendar;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.PeriodDTO;

public interface DermatologistWorkCalendarService {
	Boolean save(Dermatologist derm, Pharmacy pharm, Date start, Date end);
	List<DermatologistWorkCalendar> findAllDermWorkCalendarByDermId(Long id);
	List<PeriodDTO> findAllDermWorkCalendarByDermIdAndPeriod(Long pharmacyId, Long dermatologistId, Date start, Date end);
	void deleteDermWorkCalendarByDate(Date start, Long dermId);
	void deleteDermWorkCalendarByDerm(Long dermId);
	PeriodDTO findDermWorkCalendarByDermIdAndDate(Long pharmacyId, Long dermatologistId, Date start);
	PeriodDTO findDermWorkCalendarForDermAndDate(Long pharmacyId, Long dermatologistId, Date start);
}
