package isa.apoteka.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.service.DermatologistService;
import isa.apoteka.service.DermatologistWorkCalendarService;
import isa.apoteka.service.PharmacyService;

@RestController
@RequestMapping(value = "api/dermWP")
public class DermatologistWorkCalendarController {
	
	private DermatologistWorkCalendarService dermWCService;
	private DermatologistService dermatologistService;
	private PharmacyService pharmacyService;
	
	@Autowired
	public DermatologistWorkCalendarController(DermatologistWorkCalendarService dermWCService, DermatologistService dermatologistService, PharmacyService pharmacyService) {
		this.dermatologistService = dermatologistService;
		this.dermWCService = dermWCService;
		this.pharmacyService = pharmacyService;
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public Boolean save(@RequestBody Map<String, Object> params) throws Exception {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long pharmacyId = admin.getPharmacy().getId();
		Pharmacy pharmacy = pharmacyService.findOne(pharmacyId);
		Long dermatologistId = Long.parseLong(params.get("dermatologistId").toString());	
		try{
			return dermWCService.save(dermatologistId, pharmacy, new Date(Long.parseLong(params.get("startDate").toString())), new Date(Long.parseLong(params.get("endDate").toString())));
		}catch(Exception e) {
			return false;
		}
	}
	
	@GetMapping("/findAllDermWorkCalendarByDermIdAndPeriod")
	//@PreAuthorize("hasRole('ADMIN')")
	public List<PeriodDTO> findAllDermWorkCalendarByDermIdAndPeriod(Long dermatologistId, Long startDate, Long endDate) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long pharmacyId = admin.getPharmacy().getId();
		return dermWCService.findAllDermWorkCalendarByDermIdAndPeriod(pharmacyId, dermatologistId, new Date(startDate), new Date(endDate));
	}
	
	@PostMapping("/deleteDermWorkCalendarByDate")
	//@PreAuthorize("hasRole('ADMIN')")
	public void deleteDermWorkCalendarByDate(@RequestBody Map<String, Object> params) throws ParseException {
		Date startDate = new Date(Long.parseLong(params.get("startDate").toString()));
		Long dermatologistId = Long.parseLong(params.get("dermatologistId").toString());
		dermWCService.deleteDermWorkCalendarByDate(startDate,dermatologistId);
	}
	
	@GetMapping("/findDermWorkCalendarByDermIdAndDate")
	@PreAuthorize("hasRole('DERM')")
	public PeriodDTO findDermWorkCalendarByDermIdAndDate(Long pharmacyId, Long start) {
		Dermatologist derm = (Dermatologist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return dermWCService.findDermWorkCalendarByDermIdAndDate(pharmacyId, derm.getId(), new Date(start));
	}
	
	@GetMapping("/getDermWorkCalendarByDermIdAndDate")
	@PreAuthorize("hasRole('ADMIN')")
	public PeriodDTO getDermWorkCalendarByDermIdAndDate(Long dermId,Long start) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return dermWCService.findDermWorkCalendarByDermIdAndDate(admin.getPharmacy().getId(),dermId, new Date(start));
	}
}
