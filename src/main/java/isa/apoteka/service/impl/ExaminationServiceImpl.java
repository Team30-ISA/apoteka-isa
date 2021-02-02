package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.apoteka.domain.Examination;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.repository.ExamintaionRepository;
import isa.apoteka.service.ExaminationService;

@Service
public class ExaminationServiceImpl implements ExaminationService {
	
	@Autowired
	private ExamintaionRepository examinationRepository;

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
		String patientName = "";
		if(examination.getPharmacistWorkCalendar() == null)
			return null;
		if(examination.getPharmacistWorkCalendar().getPharmacy() == null)
			return null;
		if(examination.getPatient() != null)
			patientName = examination.getPatient().getFirstName() + examination.getPatient().getLastName();
		return new ExaminationDTO(examination.getId(), examination.getStartDate(), examination.getDuration(), examination.getPharmacistWorkCalendar().getPharmacy().getName(), patientName, examination.getPrice());
	}
	
	public List<ExaminationDTO> mapListExamintaionToListExamintaionDTO(List<Examination> examintaions) {
		List<ExaminationDTO> examinationDTOs = new ArrayList<>();
		for(Examination e : examintaions) {
			ExaminationDTO dto = mapExaminationToExaminationDTO(e);
			if(dto == null)
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
		for(int i = 0; i < num; i++) {
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
		for(int i = 0; i < 12; i++) {
			Long n = examinationRepository.countTerms(dermatologistId, startDate, endDate);
			ret.add(n);
			calendar.add(Calendar.DATE, 1);
			startDate = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = calendar.getTime();
		}
		return ret;
	}
	
	class Sortbyroll implements Comparator<ExaminationDTO>
	{
		@Override
		public int compare(ExaminationDTO o1, ExaminationDTO o2) {
			if (o1.getStartDate().before(o1.getStartDate()))
	            return -1;
	        else if (o1.getStartDate().after(o2.getStartDate()))
	            return 1;
	        else
	            return 0;
		}
	}

}
