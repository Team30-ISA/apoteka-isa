package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ExaminationDTO;

public interface CounselingService {
	List<ExaminationDTO> findAllTermsByDay(Long pharmacyId, Long dermatologistId, Date start);
	List<Long> countTermsByDays(Long pharmacyId, Long dermatologistId, Date start, int num);
	List<Long> countTermsByMonths(Long pharmacyId, Long dermatologistId, Date start);
	public List<Pharmacy> findAllPharmaciesByDermatologist(Long dermatologistId);
	ExaminationDTO findOneDTO(Long id);
	Counseling findOne(Long id);
	Patient getPatientInCounseling(Long id);
	Pharmacy getPharmacyInCounseling(Long id);
	ExaminationDTO getNearestCounseling(Long dermatologistId, Date start);
}
