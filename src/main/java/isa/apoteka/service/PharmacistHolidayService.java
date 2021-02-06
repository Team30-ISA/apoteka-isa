package isa.apoteka.service;

import java.util.Date;

import isa.apoteka.domain.Pharmacist;

public interface PharmacistHolidayService {
	void save(Pharmacist pharmacist, Date start, Date end);
}
