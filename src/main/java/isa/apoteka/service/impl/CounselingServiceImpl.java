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

import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.repository.CounselingRepository;
import isa.apoteka.repository.DermatologistRepository;
import isa.apoteka.service.CounselingService;

@Service
public class CounselingServiceImpl implements CounselingService {
	
	@Autowired
	private CounselingRepository counselingRepository;
	
	@Autowired
	private DermatologistRepository dermatologistRepository;

	@Override
	public List<ExaminationDTO> findAllTermsByDay(Long pharmacyId, Long dermatologistId, Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		List<Counseling> counseling = counselingRepository.findAllTerms(pharmacyId, dermatologistId, startDate, endDate);
		List<ExaminationDTO> dtos = mapListCounselingToListCounselingDTO(counseling);
		Collections.sort(dtos, new Sortbyroll());
		return dtos;
	}
	
	public ExaminationDTO mapCounselingToCounselingDTO(Counseling counseling) {
		String patientName = "";
		if(counseling.getDermatologistWorkCalendar() == null)
			return null;
		if(counseling.getDermatologistWorkCalendar().getPharmacy() == null)
			return null;
		if(counseling.getPatient() != null)
			patientName = counseling.getPatient().getFirstName() + " " + counseling.getPatient().getLastName();
		return new ExaminationDTO(counseling.getId(), counseling.getStartDate(), counseling.getDuration(), counseling.getDermatologistWorkCalendar().getPharmacy().getName(), patientName, counseling.getPrice(), counseling.getReport());
	}
	
	public List<ExaminationDTO> mapListCounselingToListCounselingDTO(List<Counseling> counselings) {
		List<ExaminationDTO> counselingDTOs = new ArrayList<>();
		for(Counseling c : counselings) {
			ExaminationDTO dto = mapCounselingToCounselingDTO(c);
			if(dto == null)
				continue;
			counselingDTOs.add(dto);
		}
		return counselingDTOs;
	}

	@Override
	public List<Long> countTermsByDays(Long pharmacyId, Long dermatologistId, Date start, int num) {
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
			Long n = counselingRepository.countTerms(pharmacyId, dermatologistId, startDate, endDate);
			ret.add(n);
			startDate = calendar.getTime();
			calendar.add(Calendar.DATE, 1);
			endDate = calendar.getTime();
		}
		return ret;
	}
	
	@Override
	public List<Long> countTermsByMonths(Long pharmacyId, Long dermatologistId, Date start) {
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
			Long n = counselingRepository.countTerms(pharmacyId, dermatologistId, startDate, endDate);
			ret.add(n);
			calendar.add(Calendar.DATE, 1);
			startDate = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = calendar.getTime();
		}
		return ret;
	}

	@Override
	public List<Pharmacy> findAllPharmaciesByDermatologist(Long dermatologistId) {
		return dermatologistRepository.getDermPharmacies(dermatologistId);
	}
	
	@Override
	public ExaminationDTO findOneDTO(Long id) {
		return mapCounselingToCounselingDTO(counselingRepository.findById(id).orElse(null));
	}
	
	@Override
	public Counseling findOne(Long id) {
		return counselingRepository.findById(id).orElse(null);
	}
	
	@Override
	public Patient getPatientInCounseling(Long id) {
		Counseling counseling = counselingRepository.findById(id).orElse(null);
		if(counseling == null)
			return null;
		return counseling.getPatient();
	}
	
	@Override
	public Pharmacy getPharmacyInCounseling(Long id) {
		Counseling counseling = counselingRepository.findById(id).orElse(null);
		if(counseling == null)
			return null;
		return counseling.getDermatologistWorkCalendar().getPharmacy();
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

	@Override
	public ExaminationDTO getNearestCounseling(Long dermatologistId, Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		List<Counseling> counselings = counselingRepository.findAllByDermAndStart(dermatologistId, startDate);
		if(counselings == null)
			return null;
		for(Counseling c : counselings) {
			if(c.getStartDate().before(start)) {
				calendar.setTime(c.getStartDate());
				calendar.add(Calendar.MINUTE, c.getDuration());
				System.out.println(calendar.getTime());
				System.out.println(start);
				if(calendar.getTime().after(start)) {
					return mapCounselingToCounselingDTO(c);
				}
			}
		}
		for(Counseling c : counselings) {
			if(start.before(c.getStartDate())) {
				return mapCounselingToCounselingDTO(c);
			}
		}
		return mapCounselingToCounselingDTO(counselings.get(0));		
	}

}
