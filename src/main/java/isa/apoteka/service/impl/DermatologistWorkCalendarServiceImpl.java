package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.DermatologistWorkCalendar;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.repository.DermatologistWorkCalendarRepository;
import isa.apoteka.service.DermatologistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class DermatologistWorkCalendarServiceImpl implements DermatologistWorkCalendarService {


	private DermatologistWorkCalendarRepository dermWCRepository;
	
	@Autowired
	public DermatologistWorkCalendarServiceImpl(DermatologistWorkCalendarRepository dermWCRepository) {
		this.dermWCRepository = dermWCRepository;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Boolean save(Dermatologist derm, Pharmacy pharm, Date start, Date end) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(start);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endDate = calendar.getTime();
		List<DermatologistWorkCalendar> dWC = dermWCRepository.findAllDermWorkCalendarByDermIdInPeriod(derm.getId(),startDate, endDate);
		
		for(DermatologistWorkCalendar d : dWC) {
			if(start.after(d.getStartDate()) && start.before(d.getEndDate())) {
				return false;
			}else if(start.before(d.getStartDate()) && end.after(d.getStartDate())) {
				return false;
			}else if(start.equals(d.getStartDate()) || end.equals(d.getEndDate())){
				return false;
			}
		}
		DermatologistWorkCalendar dwc = new DermatologistWorkCalendar(derm, pharm, start, end);
		DermatologistWorkCalendar d = dermWCRepository.save(dwc);
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
	
	public List<PeriodDTO> MapDermWCToPeriod(List<DermatologistWorkCalendar> derms) {
		List<PeriodDTO> periods = new ArrayList<PeriodDTO>();
		for(DermatologistWorkCalendar derm : derms) {
			PeriodDTO p = new PeriodDTO(derm.getStartDate(), derm.getEndDate());
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
	public void deleteDermWorkCalendarByDerm(Long dermId) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		dermWCRepository.deleteDermWorkCalendarByDerm(dermId, admin.getPharmacy().getId());
		
	}

}
