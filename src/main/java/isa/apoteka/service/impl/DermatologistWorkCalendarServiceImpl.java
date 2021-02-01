package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isa.apoteka.domain.DermatologistWorkCalendar;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.repository.DermatologistWorkCalendarRepository;
import isa.apoteka.service.DermatologistWorkCalendarService;

@Service
public class DermatologistWorkCalendarServiceImpl implements DermatologistWorkCalendarService {

	@Autowired
	private DermatologistWorkCalendarRepository dermWCRepository;
	
	@Override
	public Boolean save(DermatologistWorkCalendar derm) {
		DermatologistWorkCalendar d = dermWCRepository.save(derm);
		if(d != null)
			return true;
		return false;
	}

	@Override
	public List<DermatologistWorkCalendar> findAllDermWorkCalendarByDermId(Long id) {
		return dermWCRepository.findAllDermWorkCalendarByDermId(id);
	}

	@Override
	public List<PeriodDTO> findAllDermWorkCalendarByDermIdAndPeriod(Long pharmacyId, Long dermatologistId, Date start, Date end) {
		return MapDermWCToPeriod(dermWCRepository.findAllDermWorkCalendarByDermIdAndPeriod(pharmacyId, dermatologistId, start, end));
	}
	
	public List<PeriodDTO> MapDermWCToPeriod(List<DermatologistWorkCalendar> derms) {
		List<PeriodDTO> periods = new ArrayList<PeriodDTO>();
		for(DermatologistWorkCalendar derm : derms) {
			PeriodDTO p = new PeriodDTO(derm.getStartDate(), derm.getEndDate());
			periods.add(p);
		}
		return periods;
	}

	@Override
	public void deleteDermWorkCalendarByDate(Date start, Long dermId) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		dermWCRepository.deleteDermWorkCalendarByDate(startDate, endDate, dermId);
	}

}
