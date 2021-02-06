package isa.apoteka.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacistHoliday;
import isa.apoteka.repository.PharmacistHolidayRepository;
import isa.apoteka.service.PharmacistHolidayService;

@Service
public class PharmacistHolidayServiceImpl implements PharmacistHolidayService {
	
	@Autowired
	private PharmacistHolidayRepository pharmacistHolidayRepository;

	@Override
	public void save(Pharmacist pharmacist, Date start, Date end) {
		pharmacistHolidayRepository.save(new PharmacistHoliday(start, end, pharmacist));
		
	}

	@Override
	public Boolean isPharmOnHolidays(Long pharmacistId, Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		List<PharmacistHoliday> ph = pharmacistHolidayRepository.findAllByPharmId(pharmacistId, true);
		for(PharmacistHoliday p : ph) {
			if(p.getStartDate().getTime() <= startDate.getTime() && p.getEndDate().getTime() >= startDate.getTime()) {
				return true;
			}
		}
		return false;
	}

}
