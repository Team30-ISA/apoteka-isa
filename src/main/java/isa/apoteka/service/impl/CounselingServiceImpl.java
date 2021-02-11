package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Counseling;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.repository.CounselingRepository;
import isa.apoteka.repository.DermatologistRepository;
import isa.apoteka.service.CounselingService;

@Service
@Transactional(readOnly = true)
public class CounselingServiceImpl implements CounselingService {

	@Autowired
	private CounselingRepository counselingRepository;

	@Autowired
	private DermatologistRepository dermatologistRepository;

	@Autowired
	private EmailService emailService;

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
		List<Counseling> counseling = counselingRepository.findAllTerms(pharmacyId, dermatologistId, startDate,
				endDate);
		List<ExaminationDTO> dtos = mapListCounselingToListExaminationDTO(counseling);
		Collections.sort(dtos, new Sortbyroll());
		return dtos;
	}

	public ExaminationDTO mapCounselingToExaminationDTO(Counseling counseling) {
		if (counseling == null)
			return null;
		String patientName = "";
		if (counseling.getDermatologistWorkCalendar() == null)
			return null;
		if (counseling.getDermatologistWorkCalendar().getPharmacy() == null)
			return null;
		if (counseling.getPatient() != null)
			patientName = counseling.getPatient().getFirstName() + " " + counseling.getPatient().getLastName();
		return new ExaminationDTO(counseling.getId(), counseling.getStartDate(), counseling.getDuration(),
				counseling.getDermatologistWorkCalendar().getPharmacy().getName(), patientName, counseling.getPrice(),
				counseling.getReport());
	}

	public List<ExaminationDTO> mapListCounselingToListExaminationDTO(List<Counseling> counselings) {
		List<ExaminationDTO> counselingDTOs = new ArrayList<>();
		for (Counseling c : counselings) {
			ExaminationDTO dto = mapCounselingToExaminationDTO(c);
			if (dto == null)
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
		for (int i = 0; i < num; i++) {
			Long n = counselingRepository.countTerms(pharmacyId, dermatologistId, startDate, endDate);
			ret.add(n);
			startDate = calendar.getTime();
			calendar.add(Calendar.DATE, 1);
			endDate = calendar.getTime();
		}
		return ret;
	}

	@Override
	public List<Long> countAllTermsByDays(Long dermatologistId, Date start, int num) {
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
			Long n = counselingRepository.countAllTerms(dermatologistId, startDate, endDate);
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
		for (int i = 0; i < 12; i++) {
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
		return mapCounselingToExaminationDTO(counselingRepository.findById(id).orElse(null));
	}

	@Override
	public Counseling findOne(Long id) {
		return counselingRepository.findById(id).orElse(null);
	}

	@Override
	public Patient getPatientInCounseling(Long id) {
		Counseling counseling = counselingRepository.findById(id).orElse(null);
		if (counseling == null)
			return null;
		return counseling.getPatient();
	}

	@Override
	public Pharmacy getPharmacyInCounseling(Long id) {
		Counseling counseling = counselingRepository.findById(id).orElse(null);
		if (counseling == null)
			return null;
		return counseling.getDermatologistWorkCalendar().getPharmacy();
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
	public Counseling getNearestCounseling(Long dermatologistId, Date start, boolean finished) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		List<Counseling> counselings = counselingRepository.findAllByDermAndStart(dermatologistId, startDate);
		if (counselings == null || counselings.size() == 0)
			return null;
		for (Counseling c : counselings) {
			if (finished) {
				if (c.getReport() != null && !(c.getReport()).equals(""))
					continue;
			}
			if (c.getStartDate().before(start)) {
				calendar.setTime(c.getStartDate());
				calendar.add(Calendar.MINUTE, c.getDuration());
				System.out.println(calendar.getTime());
				System.out.println(start);
				if (calendar.getTime().after(start)) {
					return c;
				}
			}
		}
		for (Counseling c : counselings) {
			if (finished) {
				if (c.getReport() != null && !(c.getReport()).equals(""))
					continue;
			}
			if (start.getTime() <= c.getStartDate().getTime()) {
				return c;
			}
		}
		return null;
	}

	@Override
	public ExaminationDTO getNearestCounselingDTO(Long pharmacistId, Date start, boolean finished) {
		Counseling counseling = getNearestCounseling(pharmacistId, start, finished);
		if (counseling == null)
			return null;
		return mapCounselingToExaminationDTO(counseling);
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean update(Patient patient, Long counselingId) throws Exception {
		Counseling c = counselingRepository.findById(counselingId).orElse(null);
		if (c.getPatient() != null) {
			return false;
		}
		c.setPatient(patient);
		counselingRepository.save(c);
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public void makeAppointment(Long patId, Long counsId) {
		counselingRepository.makeAppointment(patId, counsId);

	}

	@Override
	@Transactional(readOnly = false)
	public Boolean updateReport(String report, Long counselingId)  throws Exception {
		Counseling counseling = counselingRepository.findById(counselingId).orElse(null);
		if(counseling.getReport() != null && !counseling.getReport().equals("")) {
			return false;
		}
		counseling.setReport(report);
		counselingRepository.save(counseling);
		return true;
	}

	@Override
	public Boolean isDermFree(Long dermatologistId, Date start, Date end) {
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
		if (counselingRepository.countAllTerms(dermatologistId, start, end) > 0)
			return false;
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean createCounseling(Date start, int duration, Float price, Long dwcId, Long dermId, Long pharmacyId) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.MINUTE, duration);
		Date end = calendar.getTime();
		if (isDermatologistFree(start, end, dermId, pharmacyId) == false) {
			return false;
		}
		counselingRepository.createCounseling(start, duration, price, dwcId);
		return true;
	}

	public Boolean isDermatologistFree(Date start, Date end, Long dermId, Long pharmacyId) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 2);
		Date endDate = calendar.getTime();
		List<Counseling> counselings = counselingRepository.findAllTerms(pharmacyId, dermId, startDate, endDate);
		for (Counseling c : counselings) {
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

	public List<Counseling> findAllByPharmId(Long pharmId) {
		return counselingRepository.findAllByPharmId(pharmId);
	}

	public Dermatologist findDermatologistForCounseling(Long counsId) {
		return counselingRepository.findDermatologistForCounseling(counsId);
	}

	@Override
	public void sendCounselingReservation(Counseling c) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			emailService.sendCounselingReservation(c, patient);
		} catch (Exception e) {
			System.out.println("Greska pri slanju emaila");
		}
	}

	@Override
	public List<Counseling> findAllByPatientId(Long patId) {
		return counselingRepository.findAllByPatientId(patId);
	}

	@Override
	@Transactional(readOnly = false)
	public void cancelAppointment(Long counsId) {
		counselingRepository.cancelAppointment(counsId);
	}

	@Override
	public List<Counseling> finishedCounseling(Long id, Date pocetak, Date kraj) {
		return counselingRepository.finishedCounseling(id, pocetak, kraj);
	}

	@Override
	public List<Counseling> AllfinishedCounseling(Long id) {
		return counselingRepository.allFinishedCounseling(id);
	}

	public Counseling save(Counseling counseling) {
		return counselingRepository.save(counseling);
	}

}
