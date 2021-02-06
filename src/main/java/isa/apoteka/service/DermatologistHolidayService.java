package isa.apoteka.service;

import java.util.Date;

import isa.apoteka.domain.Dermatologist;

public interface DermatologistHolidayService {
	void save(Dermatologist dermatologist, Date start, Date end);
}
