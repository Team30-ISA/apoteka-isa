package isa.apoteka.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.PharmacistWorkCalendar;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.PeriodDTO;
import isa.apoteka.service.PharmacistService;
import isa.apoteka.service.PharmacistWorkCalendarService;

@RestController
@RequestMapping(value = "api/pharmWP")
public class PharmacistWorkCalendarController {
	
	@Autowired
	private PharmacistWorkCalendarService pharmWCService;
	
	@Autowired
	private PharmacistService pharmacistService;
	
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public Boolean save(@RequestBody Map<String, Object> params) throws ParseException {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long pharmacyId = admin.getPharmacy().getId();
		Pharmacist pharmacist = pharmacistService.findById(Long.parseLong(params.get("pharmacistId").toString()));		
		PharmacistWorkCalendar pwc = new PharmacistWorkCalendar(pharmacist, pharmacist.getPharmacy(), new Date(Long.parseLong(params.get("startDate").toString())), new Date(Long.parseLong(params.get("endDate").toString())));
		return pharmWCService.save(pwc);
	}
	
	@GetMapping("/findAllPharmWorkCalendarByPharmIdAndPeriod")
	//@PreAuthorize("hasRole('ADMIN')")
	public List<PeriodDTO> findAllPharmWorkCalendarByPharmIdAndPeriod(Long pharmacistId, Long startDate, Long endDate) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long pharmacyId = admin.getPharmacy().getId();
		return pharmWCService.findAllPharmWorkCalendarByPharmIdAndPeriod(pharmacistId, new Date(startDate), new Date(endDate));
	}
	
	@PostMapping("/deletePharmWorkCalendarByDate")
	//@PreAuthorize("hasRole('ADMIN')")
	public void deletePharmWorkCalendarByDate(@RequestBody Map<String, Object> params) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date startDate = new Date(Long.parseLong(params.get("startDate").toString()));
		Long pharmacistId = Long.parseLong(params.get("pharmacistId").toString());
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long pharmacyId = admin.getPharmacy().getId();
		pharmWCService.deletePharmWorkCalendarByDate(startDate);
	}
}
