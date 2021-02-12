package isa.apoteka.service;

import java.util.Date;
import java.util.List;

import isa.apoteka.domain.Examination;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ExaminationDTO;

public interface ExaminationService {
	List<ExaminationDTO> findAllTermsByDay(Long pharmacistId, Date start);
	List<Long> countTermsByDays( Long pharmacistId, Date start, int num);
	List<Long> countTermsByMonths(Long pharmacistId, Date start);
	Examination getNearestExamintaion(Long pharmacistId, Date start, boolean finished);
	ExaminationDTO getNearestExamintaionDTO(Long pharmacistId, Date start, boolean finished);
	Patient getPatientInExamination(Long id);
	Pharmacy getPharmacyInExamination(Long id);
	void updateReport(String report, Long examinationId);
	Examination findOne(Long id);
	Boolean isPharmFree(Long pharmacistId, Date start, Date end);
	//Boolean createExamination(Date start, int duration, Long patientId, Long pwcId, Long pharmacistId);
	List<Pharmacy> getAvailablePharmacies(Date start);
	List<Pharmacist> getAvailablePharmacistsByPharmIdAndDate(Long pharmId, Date start);
	List<Examination> getExaminationsForPatient(Long patId);
	void cancelAppointment(Long examId);
	Boolean createExamination(Date start, int duration, Patient patient, Long pwcId, Long pharmacistId) throws Exception;
	List<Examination> finishedExamination(Long pharmacyId, Date startDate, Date endDate);
}
