package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.LeaveRequestStatus;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacistHoliday;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.dto.PharmHolidayDTO;
import isa.apoteka.repository.PharmacistHolidayRepository;
import isa.apoteka.repository.PharmacistWorkCalendarRepository;
import isa.apoteka.service.PharmacistHolidayService;
import isa.apoteka.service.PharmacistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class PharmacistHolidayServiceImpl implements PharmacistHolidayService {
	
	private PharmacistHolidayRepository pharmacistHolidayRepository;
	private PharmacistWorkCalendarService pharmWCService;
	
	@Autowired
	public PharmacistHolidayServiceImpl(PharmacistWorkCalendarService pharmWCService, PharmacistHolidayRepository pharmacistHolidayRepository){
		this.pharmWCService = pharmWCService;
		this.pharmacistHolidayRepository = pharmacistHolidayRepository;
	}
	
	
	

	@Override
	@Transactional(readOnly = false)
	public void save(Pharmacist pharmacist, Date start, Date end) {
		pharmacistHolidayRepository.save(new PharmacistHoliday(start, end, pharmacist, LeaveRequestStatus.PENDING));
		
	}

	@Override
	public Boolean isPharmOnHolidays(Long pharmacistId, Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		List<PharmacistHoliday> ph = pharmacistHolidayRepository.findAllByPharmId(pharmacistId, LeaveRequestStatus.APPROVED);
		for(PharmacistHoliday p : ph) {
			if(p.getStartDate().getTime() <= startDate.getTime() && p.getEndDate().getTime() >= startDate.getTime()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<PharmHolidayDTO> findAll(Long pharmacyId) {
		List<PharmacistHoliday> holidays =  pharmacistHolidayRepository.findAllByPharmacy(pharmacyId);
		List<PharmHolidayDTO> dtos = new ArrayList<PharmHolidayDTO>();
		for(PharmacistHoliday ph : holidays) {
			dtos.add(new PharmHolidayDTO(ph.getId(),ph.getPharmacist().getId(), ph.getPharmacist().getFirstName(), ph.getPharmacist().getLastName(), ph.getStartDate(), ph.getEndDate(), ph.getStatus()));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly = false)
	public void reject(Long holidayId) {
		pharmacistHolidayRepository.reject(holidayId);
	}

	@Override
	@Transactional(readOnly = false)
	public void approve(Long holidayId, Long pharmId, Date start, Date end) {
		List<PeriodDTO> lista = pharmWCService.findAllPharmWorkCalendarByPharmIdAndPeriod(pharmId, start, end);
		for(PeriodDTO p : lista) {
			pharmWCService.deletePharmWorkCalendarByDate(p.getStartDate(), pharmId);
		}
		pharmacistHolidayRepository.approve(holidayId);
		
	}

}
