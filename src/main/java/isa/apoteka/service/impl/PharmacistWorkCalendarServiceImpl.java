package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.PharmacistWorkCalendar;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.repository.PharmacistWorkCalendarRepository;
import isa.apoteka.service.PharmacistWorkCalendarService;

@Service
public class PharmacistWorkCalendarServiceImpl implements PharmacistWorkCalendarService {

	@Autowired
	private PharmacistWorkCalendarRepository pharmWCRepository;
	
	@Override
	public Boolean save(PharmacistWorkCalendar derm) {
		PharmacistWorkCalendar d = pharmWCRepository.save(derm);
		if(d != null)
			return true;
		return false;
	}

	@Override
	public List<PharmacistWorkCalendar> findAllPharmWorkCalendarByPharmId(Long id) {
		return pharmWCRepository.findAllPharmWorkCalendarByPharmId(id);
	}

	@Override
	public List<PeriodDTO> findAllPharmWorkCalendarByPharmIdAndPeriod(Long pharmacistId, Date start, Date end) {
		return MapPharmWCToPeriod(pharmWCRepository.findAllPharmWorkCalendarByPharmIdAndPeriod(pharmacistId, start, end));
	}
	
	public List<PeriodDTO> MapPharmWCToPeriod(List<PharmacistWorkCalendar> pharms) {
		List<PeriodDTO> periods = new ArrayList<PeriodDTO>();
		for(PharmacistWorkCalendar pharm : pharms) {
			PeriodDTO p = new PeriodDTO(pharm.getStartDate(), pharm.getEndDate());
			periods.add(p);
		}
		return periods;
	}

	@Override
	public void deletePharmWorkCalendarByDate(Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		pharmWCRepository.deletePharmWorkCalendarByDate(startDate, endDate);
	}
}
