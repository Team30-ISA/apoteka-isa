package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import isa.apoteka.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.repository.ExamintaionRepository;
import isa.apoteka.service.ExaminationService;
import isa.apoteka.service.PharmacistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class ExaminationServiceImpl implements ExaminationService {

	@Autowired
	private ExamintaionRepository examinationRepository;

	@Autowired
	private PharmacistWorkCalendarService pwcService;

	@Override
	public List<ExaminationDTO> findAllTermsByDay(Long dermatologistId, Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		List<Examination> examintaions = examinationRepository.findAllTerms(dermatologistId, startDate, endDate);
		List<ExaminationDTO> dtos = mapListExamintaionToListExamintaionDTO(examintaions);
		Collections.sort(dtos, new Sortbyroll());
		return dtos;
	}

	public ExaminationDTO mapExaminationToExaminationDTO(Examination examination) {
		if (examination == null)
			return null;
		String patientName = "";
		if (examination.getPharmacistWorkCalendar() == null)
			return null;
		if (examination.getPharmacistWorkCalendar().getPharmacy() == null)
			return null;
		if (examination.getPatient() != null)
			patientName = examination.getPatient().getFirstName() + " " + examination.getPatient().getLastName();
		return new ExaminationDTO(examination.getId(), examination.getStartDate(), examination.getDuration(),
				examination.getPharmacistWorkCalendar().getPharmacy().getName(), patientName, examination.getPrice(),
				examination.getReport());
	}

	public List<ExaminationDTO> mapListExamintaionToListExamintaionDTO(List<Examination> examintaions) {
		List<ExaminationDTO> examinationDTOs = new ArrayList<>();
		for (Examination e : examintaions) {
			ExaminationDTO dto = mapExaminationToExaminationDTO(e);
			if (dto == null)
				continue;
			examinationDTOs.add(dto);
		}
		return examinationDTOs;
	}

	@Override
	public List<Long> countTermsByDays(Long dermatologistId, Date start, int num) {
		List<Long> ret = new ArrayList<>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		for (int i = 0; i < num; i++) {
			Long n = examinationRepository.countTerms(dermatologistId, startDate, endDate);
			ret.add(n);
			startDate = calendar.getTime();
			calendar.add(Calendar.DATE, 1);
			endDate = calendar.getTime();
		}
		return ret;
	}

	@Override
	public List<Long> countTermsByMonths(Long dermatologistId, Date start) {
		List<Long> ret = new ArrayList<>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = calendar.getTime();
		for (int i = 0; i < 12; i++) {
			Long n = examinationRepository.countTerms(dermatologistId, startDate, endDate);
			ret.add(n);
			calendar.add(Calendar.DATE, 1);
			startDate = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = calendar.getTime();
		}
		return ret;
	}

	class Sortbyroll implements Comparator<ExaminationDTO> {
		@Override
		public int compare(ExaminationDTO o1, ExaminationDTO o2) {
			if (o1.getStartDate().getTime() <= o2.getStartDate().getTime())
				return -1;
			else if (o1.getStartDate().getTime() >= o2.getStartDate().getTime())
				return 1;
			else
				return 0;
		}
	}

	@Override
	public Examination getNearestExamintaion(Long pharmacistId, Date start, boolean finished) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		List<Examination> examinations = examinationRepository.findAllByPharmAndStart(pharmacistId, startDate);
		if (examinations == null || examinations.size() == 0)
			return null;
		for (Examination e : examinations) {
			if (finished) {
				if (e.getReport() != null && !(e.getReport()).equals(""))
					continue;
			}
			if (e.getStartDate().before(start)) {
				calendar.setTime(e.getStartDate());
				calendar.add(Calendar.MINUTE, e.getDuration());
				System.out.println(calendar.getTime());
				System.out.println(start);
				if (calendar.getTime().after(start)) {
					return e;
				}
			}
		}
		for (Examination e : examinations) {
			if (finished) {
				if (e.getReport() != null && !(e.getReport()).equals(""))
					continue;
			}
			if (start.getTime() <= e.getStartDate().getTime()) {
				return e;
			}
		}
		return null;
	}

	@Override
	public ExaminationDTO getNearestExamintaionDTO(Long pharmacistId, Date start, boolean finished) {
		Examination examination = getNearestExamintaion(pharmacistId, start, finished);
		if (examination == null)
			return null;
		return mapExaminationToExaminationDTO(examination);
	}

	@Override
	public Patient getPatientInExamination(Long id) {
		Examination examination = examinationRepository.findById(id).orElse(null);
		if (examination == null)
			return null;
		return examination.getPatient();
	}

	@Override
	public Pharmacy getPharmacyInExamination(Long id) {
		Examination examination = examinationRepository.findById(id).orElse(null);
		if (examination == null)
			return null;
		return examination.getPharmacistWorkCalendar().getPharmacy();
	}

	@Override
	@Transactional(readOnly = false)
	public void updateReport(String report, Long examinationId) {
		examinationRepository.updateReport(report, examinationId);
	}

	@Override
	public Examination findOne(Long id) {
		return examinationRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean createExamination(Date start, int duration, Long patientId, Long pwcId, Long pharmacistId) throws Exception {
		// PROVERI DA LI JE FARMACEUT SLOBODAN
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.MINUTE, duration);
		Date end = calendar.getTime();
		if (isPharmacistFree(start, end, pharmacistId) == false) {
			return false;
		}
		// DOBAVI RADNO VREME
		PharmacistWorkCalendar pwc = pwcService.findById(pwcId);
		// SACUVAJ TERMIN
		examinationRepository.createExamination(start, duration, patientId, pwcId);
		// SACUVAJ RADNO VREME (AZURIRAJ VERISON)
		pwc.setLastReqDate(new Date());
		pwcService.save(pwc);
		return true;
	}

	public Boolean isPharmacistFree(Date start, Date end, Long pharmacistId) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 2);
		Date endDate = calendar.getTime();
		List<Examination> examinations = examinationRepository.findAllTerms(pharmacistId, startDate, endDate);
		for (Examination c : examinations) {
			calendar.setTime(c.getStartDate());
			calendar.add(Calendar.MINUTE, c.getDuration());
			endDate = calendar.getTime();
			if ((c.getStartDate().getTime() <= start.getTime() && endDate.getTime() > start.getTime())
					|| (c.getStartDate().getTime() < end.getTime() && endDate.getTime() >= end.getTime())
					|| (c.getStartDate().getTime() >= start.getTime() && endDate.getTime() <= end.getTime())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Boolean isPharmFree(Long pharmacistId, Date start, Date end) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		start = calendar.getTime();
		calendar.setTime(end);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, 1);
		end = calendar.getTime();
		if (examinationRepository.countTerms(pharmacistId, start, end) > 0)
			return false;
		return true;
	}

	@Override
	public List<Examination> finishedExamination(Long pharmacyId, Date startDate, Date endDate) {
		return examinationRepository.finishedExaminations(pharmacyId, startDate, endDate);
	}

	public List<Pharmacist> getAllExaminationPharmacistsByPatientId(Long id) {
		List<Examination> examinations = examinationRepository.findByPatientId(id);
		return examinations.stream().map(e -> e.getPharmacistWorkCalendar().getPharmacist()).collect(Collectors.toList());
	}
}
