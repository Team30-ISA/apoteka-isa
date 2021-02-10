package isa.apoteka.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ExaminationDTO;

public interface CounselingService {
	List<ExaminationDTO> findAllTermsByDay(Long pharmacyId, Long dermatologistId, Date start);
	List<Long> countTermsByDays(Long pharmacyId, Long dermatologistId, Date start, int num);
	List<Long> countAllTermsByDays(Long dermatologistId, Date start, int num);
	List<Long> countTermsByMonths(Long pharmacyId, Long dermatologistId, Date start);
	List<Pharmacy> findAllPharmaciesByDermatologist(Long dermatologistId);
	ExaminationDTO findOneDTO(Long id);
	Counseling findOne(Long id);
	Patient getPatientInCounseling(Long id);
	Pharmacy getPharmacyInCounseling(Long id);
	ExaminationDTO getNearestCounselingDTO(Long dermatologistId, Date start, boolean finished);
	Counseling getNearestCounseling(Long dermatologistId, Date start, boolean finished);
	Boolean isDermFree(Long dermatologistId, Date start, Date end);
	Boolean update(Patient patient, Long counselingId);
	void updateReport(String report, Long counselingId);
	public Boolean createCounseling(Date start, int duration, Float price, Long dwcId, Long dermId, Long pharmacyId);
	List<Counseling> findAllByPharmId(Long pharmId);
	Dermatologist findDermatologistForCounseling(Long counsId);
	void makeAppointment(Long patId, Long counsId);
	void sendCounselingReservation(Counseling c);
	List<Counseling> findAllByPatientId(Long patId);
	void cancelAppointment(Long counsId);
	List<Counseling> finishedCounseling(Long id, Date pocetak, Date kraj);
	List<Counseling> AllfinishedCounseling(Long id);
	Counseling save(Counseling counseling);

}
