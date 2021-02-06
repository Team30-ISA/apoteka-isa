package isa.apoteka.service.impl;

import java.util.Date;

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

}
