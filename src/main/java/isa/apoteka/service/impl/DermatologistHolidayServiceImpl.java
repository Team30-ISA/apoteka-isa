package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.DermatologistHoliday;
import isa.apoteka.domain.LeaveRequestStatus;
import isa.apoteka.domain.PharmacistHoliday;
import isa.apoteka.dto.DermHolidayDTO;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.dto.PharmHolidayDTO;
import isa.apoteka.repository.DermatologistHolidayRepository;
import isa.apoteka.service.DermatologistHolidayService;
import isa.apoteka.service.DermatologistWorkCalendarService;

@Service
@Transactional(readOnly = true)
public class DermatologistHolidayServiceImpl implements DermatologistHolidayService {

	
	private DermatologistHolidayRepository dermatologistHolidayRepository;
	private DermatologistWorkCalendarService dWCService;
	@Autowired
	public DermatologistHolidayServiceImpl(DermatologistHolidayRepository dermatologistHolidayRepository, DermatologistWorkCalendarService dWCService) {
		this.dermatologistHolidayRepository = dermatologistHolidayRepository;
		this.dWCService = dWCService;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Dermatologist dermatologist, Date start, Date end) {
		dermatologistHolidayRepository.save(new DermatologistHoliday(start, end, dermatologist, LeaveRequestStatus.PENDING));
	}

	@Override
	public List<DermHolidayDTO> findAll() {
		List<DermatologistHoliday> holidays =  dermatologistHolidayRepository.findAll();
		List<DermHolidayDTO> dtos = new ArrayList<DermHolidayDTO>();
		for(DermatologistHoliday dh : holidays) {
			dtos.add(new DermHolidayDTO(dh.getId(),dh.getDermatologist().getId(), dh.getDermatologist().getFirstName(), dh.getDermatologist().getLastName(), dh.getStartDate(), dh.getEndDate(), dh.getStatus()));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly = false)
	public void reject(Long holidayId) {
		dermatologistHolidayRepository.reject(holidayId);
	}

	@Override
	@Transactional(readOnly = false)
	public void approve(Long holidayId, Long dermId, Date start, Date end) {
		List<PeriodDTO> lista = dWCService.findAllDermWorkCalendarByPeriod(dermId, start, end);
		for(PeriodDTO p : lista) {
			dWCService.deleteDermWorkCalendarByDateAllPharmacies(p.getStartDate(), dermId);
		}
		dermatologistHolidayRepository.approve(holidayId);
		
	}
}
