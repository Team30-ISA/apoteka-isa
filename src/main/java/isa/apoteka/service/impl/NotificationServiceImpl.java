package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.domain.Notification;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.repository.NotificationReposiotry;
import isa.apoteka.service.NotificationService;
import isa.apoteka.service.PharmacyService;

@Service
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationReposiotry notificationReposiotry;
	@Autowired
	private PharmacyService pharmacyService;

	@Override
	@Transactional(readOnly = false)
	public void noMedicineInStock(Date date, Long pharmacyId, String message) {
		List<PharmacyAdmin> admins = new ArrayList<PharmacyAdmin>();
		try {
			admins = pharmacyService.findOne(pharmacyId).getPharmacyAdmins();
		} catch (Exception e) {
			return;
		}
		for(PharmacyAdmin p : admins) {
			notificationReposiotry.addMessage(date, p.getId(), message);
		}
		
	}

	@Override
	public List<Notification> findAllMessages(Long id) {
		return notificationReposiotry.findAllByUser(id);
	}
}
