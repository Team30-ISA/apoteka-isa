package isa.apoteka.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.Notification;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.service.NotificationService;

@RestController
@RequestMapping(value = "api/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@PostMapping("/noMedicineInStock")
	@PreAuthorize("hasRole('DERM') || hasRole('PHARM')")
	public void noMedicineInStock(@RequestBody Map<String, Object> params, HttpServletResponse response) {
		if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_DERM"))) {
			notificationService.noMedicineInStock(new Date(Long.parseLong(params.get("date").toString())), Long.parseLong(params.get("pharmacyId").toString()), params.get("message").toString());
		}
		else {
			Pharmacist pharm = (Pharmacist) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			notificationService.noMedicineInStock(new Date(Long.parseLong(params.get("date").toString())), pharm.getPharmacy().getId(), params.get("message").toString());
		}
	}
	
	@GetMapping("/findAllMessages")
	@PreAuthorize("hasRole('ADMIN')")
	public List<String> findAllMessages(Long id){
		List<Notification> notifications = notificationService.findAllMessages(id);
		if(notifications == null) {
			return null;
		}
		List<String> messages = new ArrayList<String>();
		for(Notification n : notifications) {
			messages.add(n.getMessage());
		}
		
		return messages;
	}
	
}
