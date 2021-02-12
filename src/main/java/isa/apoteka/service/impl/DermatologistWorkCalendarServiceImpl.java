package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.DermatologistWorkCalendar;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.repository.DermatologistRepository;
import isa.apoteka.repository.DermatologistWorkCalendarRepository;
import isa.apoteka.service.DermatologistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class DermatologistWorkCalendarServiceImpl implements DermatologistWorkCalendarService {


	private DermatologistWorkCalendarRepository dermWCRepository;
	private DermatologistRepository dermReposiotory;
	
	@Autowired
	public DermatologistWorkCalendarServiceImpl(DermatologistWorkCalendarRepository dermWCRepository, DermatologistRepository dermReposiotory) {
		this.dermWCRepository = dermWCRepository;
		this.dermReposiotory = dermReposiotory;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean save(Long dermId, Pharmacy pharm, Date start, Date end) throws Exception{
		Dermatologist derm = dermReposiotory.findById(dermId).orElse(null);
		if(derm == null) {
			return false;
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		List<DermatologistWorkCalendar> dWC = dermWCRepository.findAllDermWorkCalendarByDermIdInPeriod(dermId,startDate, endDate);
		for(DermatologistWorkCalendar d : dWC) {
			if(start.after(d.getStartDate()) && start.before(d.getEndDate())) {
				return false;
			}else if(start.before(d.getStartDate()) && end.after(d.getStartDate())) {
				return false;
			}else if(start.equals(d.getStartDate()) || end.equals(d.getEndDate())){
				return false;
			}
		}
		TimeUnit.SECONDS.sleep(15);
		
		DermatologistWorkCalendar dwc = new DermatologistWorkCalendar(derm, pharm, start, end, new Date());
		DermatologistWorkCalendar d = dermWCRepository.save(dwc);
		derm.setLastReqDate(new Date());
		dermReposiotory.save(derm);
		if(d != null)
			return true;
		return false;
	}

	@Override
	public List<DermatologistWorkCalendar> findAllDermWorkCalendarByDermId(Long id) {
		return dermWCRepository.findAllDermWorkCalendarByDermId(id);
	}

	@Override
	public List<PeriodDTO> findAllDermWorkCalendarByDermIdAndPeriod(Long pharmacyId, Long dermatologistId, Date start, Date end) {
		return MapDermWCToPeriod(dermWCRepository.findAllDermWorkCalendarByDermIdAndPeriod(pharmacyId, dermatologistId, start, end));
	}
	
	@Override
	public List<PeriodDTO> findAllDermWorkCalendarByPeriod(Long dermatologistId, Date start, Date end) {
		return MapDermWCToPeriod(dermWCRepository.findAllDermWorkCalendarByDermIdAndPeriod(dermatologistId, start, end));
	}
	
	public List<PeriodDTO> MapDermWCToPeriod(List<DermatologistWorkCalendar> derms) {
		List<PeriodDTO> periods = new ArrayList<PeriodDTO>();
		for(DermatologistWorkCalendar derm : derms) {
			PeriodDTO p = new PeriodDTO(derm.getStartDate(), derm.getEndDate(), derm.getId());
			periods.add(p);
		}
		return periods;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteDermWorkCalendarByDate(Date start, Long dermId) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		dermWCRepository.deleteDermWorkCalendarByDate(startDate, endDate, dermId, admin.getPharmacy().getId());
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deleteDermWorkCalendarByDateAllPharmacies(Date start, Long dermId) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		dermWCRepository.deleteDermWorkCalendarByDate(startDate, endDate, dermId);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteDermWorkCalendarByDerm(Long dermId) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		dermWCRepository.deleteDermWorkCalendarByDerm(dermId, admin.getPharmacy().getId());
		
	}

	@Override
	public PeriodDTO findDermWorkCalendarByDermIdAndDate(Long pharmacyId, Long dermatologistId, Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		DermatologistWorkCalendar dwc = dermWCRepository.findDermWorkCalendarByDermIdAndDate(pharmacyId, dermatologistId, startDate, endDate);
		if(dwc == null)
			return null;
		return new PeriodDTO(dwc.getStartDate(), dwc.getEndDate(), dwc.getId());
	}
	
	@Override
	public PeriodDTO findDermWorkCalendarForDermAndDate(Long pharmacyId, Long dermatologistId, Date start) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		DermatologistWorkCalendar dwc = dermWCRepository.findDermWorkCalendarByDermIdAndDate(pharmacyId, dermatologistId, startDate, endDate);
		if(dwc == null)
			return null;
		if(!(start.getTime() >= dwc.getStartDate().getTime() && start.getTime() < dwc.getEndDate().getTime())) {
			return null;
		}
		return new PeriodDTO(dwc.getStartDate(), dwc.getEndDate(), dwc.getId());
	}

	@Override
	public DermatologistWorkCalendar findById(Long dwcId) {
		return dermWCRepository.findById(dwcId).orElse(null);
	}

	@Override
	public void save(DermatologistWorkCalendar dwc) {
		dermWCRepository.save(dwc);
	}

}
