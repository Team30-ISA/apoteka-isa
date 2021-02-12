package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacistWorkCalendar;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.repository.PharmacistRepository;
import isa.apoteka.repository.PharmacistWorkCalendarRepository;
import isa.apoteka.service.PharmacistService;
import isa.apoteka.service.PharmacistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class PharmacistWorkCalendarServiceImpl implements PharmacistWorkCalendarService {

	
	private PharmacistWorkCalendarRepository pharmWCRepository;
	private PharmacistRepository pharmacistRepository;
	
	
	@Autowired
	public PharmacistWorkCalendarServiceImpl(PharmacistWorkCalendarRepository pharmWCRepository,  PharmacistRepository pharmacistRepository){
		this.pharmWCRepository = pharmWCRepository;
		this.pharmacistRepository = pharmacistRepository;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean save(PharmacistWorkCalendar pwc, Pharmacist pharmacist) throws Exception{
		TimeUnit.SECONDS.sleep(15);
		PharmacistWorkCalendar d = pharmWCRepository.save(pwc);
		pwc.setLastReqDate(new Date());
		pharmacistRepository.save(pharmacist);
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
			PeriodDTO p = new PeriodDTO(pharm.getStartDate(), pharm.getEndDate(), pharm.getId());
			periods.add(p);
		}
		return periods;
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePharmWorkCalendarByDate(Date start, Long pharmId) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		pharmWCRepository.deletePharmWorkCalendarByDate(startDate, endDate, pharmId);
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePharmWorkCalendarByPharm(Long id) {
		pharmWCRepository.deletePharmWorkCalendarByPharm(id);
		
	}
	
	@Override
	public PeriodDTO findPharmWorkCalendarByPharmIdAndDate(Long pharmacistId, Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		PharmacistWorkCalendar pwc = pharmWCRepository.findPharmWorkCalendarByPharmIdAndDate(pharmacistId, startDate, endDate);
		if(pwc == null)
			return null;
		return new PeriodDTO(pwc.getStartDate(), pwc.getEndDate(), pwc.getId());
	}

	@Override
	public PharmacistWorkCalendar findById(Long id) {
		return pharmWCRepository.findById(id).orElse(null);
	}
}
