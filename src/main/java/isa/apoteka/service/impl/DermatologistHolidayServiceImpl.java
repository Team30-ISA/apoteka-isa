package isa.apoteka.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.DermatologistHoliday;
import isa.apoteka.repository.DermatologistHolidayRepository;
import isa.apoteka.service.DermatologistHolidayService;

@Service
public class DermatologistHolidayServiceImpl implements DermatologistHolidayService {

	@Autowired
	private DermatologistHolidayRepository dermatologistHolidayRepository;

	@Override
	public void save(Dermatologist dermatologist, Date start, Date end) {
		dermatologistHolidayRepository.save(new DermatologistHoliday(start, end, dermatologist));		
	}
}
