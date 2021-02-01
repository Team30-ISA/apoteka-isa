package isa.apoteka.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.domain.Promotion;
import isa.apoteka.repository.PromotionRepository;
import isa.apoteka.service.PatientService;
import isa.apoteka.service.PromotionService;

@Service
@Transactional(readOnly = true)
public class PromotionServiceImpl implements PromotionService{
	
	private PatientService patientService;
	private EmailService emailService;
	private PromotionRepository promotionRepository;
	
	@Autowired 
	public PromotionServiceImpl(PatientService patientService, EmailService emailService, PromotionRepository promotionRepository) {
		this.patientService = patientService;
		this.emailService = emailService;
		this.promotionRepository = promotionRepository;
	}

	@Transactional(readOnly = false)
	public Promotion saveAndSendNotification(Promotion promotion) {
		Promotion newPromotion = promotionRepository.save(promotion);
		boolean success = sendEmail(newPromotion);
		if(success) {
			return newPromotion;
		}else {
			return null;
		}
	}
	
	public boolean sendEmail(Promotion promotion){
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Patient> patients = patientService.findAllPatientsNotification(admin.getPharmacy().getId());
		try {
			for(Patient p : patients) {
				emailService.sendPromotionNotificaitionAsync(p,promotion);
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}
}
