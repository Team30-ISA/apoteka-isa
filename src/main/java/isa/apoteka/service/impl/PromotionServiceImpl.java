package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.repository.PatientRepository;
import isa.apoteka.repository.PharmacyRepository;
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
	private PatientRepository patientRepository;

	@Autowired 
	public PromotionServiceImpl(PatientService patientService, EmailService emailService, PromotionRepository promotionRepository) {
		this.patientService = patientService;
		this.emailService = emailService;
		this.promotionRepository = promotionRepository;
	}

	@Autowired
	private PharmacyRepository pharmacyRepository;

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

	@Override
	@Transactional(readOnly = false)
	public void subscribe(Long pharmacyId) throws Exception {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Pharmacy pharmacy = pharmacyRepository.getOne(pharmacyId);
		if(pharmacy.getPatients().stream().filter(p -> p.getId() == patient.getId()).collect(Collectors.toList()).size() > 0)
			throw new Exception("Already subscribed");
		Patient dbPatient = patientService.findById(patient.getId()); // Mora iz baze jer se ovaj iz konteksta razlikuje pa ga necemo moci obrisati jer je uvek drugaciji
		pharmacy.getPatients().add(dbPatient);
		pharmacyRepository.save(pharmacy);
	}

	@Override
	@Transactional(readOnly = false)
	public void unsubscribe(Long pharmacyId) {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Pharmacy pharmacy = pharmacyRepository.getOne(pharmacyId);
		Patient dbPatient = patientService.findById(patient.getId());
		pharmacy.getPatients().remove(dbPatient);
		pharmacyRepository.save(pharmacy);
	}

	@Override
	public List<PharmacyDTO> findAllUserSubscribedPharmacies() {
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Patient dbPatient = patientService.findById(patient.getId());
		return dbPatient.getPharmacies().stream().map(p -> new PharmacyDTO(p)).collect(Collectors.toList());
	}
}
