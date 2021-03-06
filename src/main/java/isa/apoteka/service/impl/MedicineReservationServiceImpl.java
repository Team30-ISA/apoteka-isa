package isa.apoteka.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Patient;
import isa.apoteka.domain.ReservedMedicine;
import isa.apoteka.dto.ReservedMedicineDTO;
import isa.apoteka.repository.MedicineReservationRepository;
import isa.apoteka.service.MedicineReservationService;

@Service
@Transactional(readOnly = true)
public class MedicineReservationServiceImpl implements MedicineReservationService{
	
	private EmailService emailService;
	private MedicineReservationRepository mrRepository;
	
	@Autowired 
	public MedicineReservationServiceImpl(EmailService emailService, MedicineReservationRepository mrRepository) {
		this.emailService = emailService;
		this.mrRepository = mrRepository;
	}

	@Transactional(readOnly = false)
	public ReservedMedicine SendNotification(ReservedMedicine rm) {
		//ReservedMedicine newReservation = mrRepository.save(rm);
		boolean success = sendEmail(rm);
		if(success) {
			return rm;
		}else {
			return null;
		}
	}
	
	public boolean sendEmail(ReservedMedicine rm){
		Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
				emailService.sendMedicineReservation(rm, patient);
				return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public ReservedMedicineDTO findReservationByPharmacy(String uid, Long pharmacyId) {
		ReservedMedicineDTO dto = new ReservedMedicineDTO(mrRepository.findReservationByPharmacy(uid, pharmacyId));
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dto.getDate());
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		Date now = new Date();
		if(date.getTime() < now.getTime()) {
			return null;
		}		
		return dto;
	}

	@Transactional(readOnly = false)
	@Override
	public void approveReservation(String uid, Long pharmacyId) {
		ReservedMedicine rm = mrRepository.findReservationByPharmacy(uid, pharmacyId);
		if(rm == null)
			return;
		mrRepository.approveReservation(uid);
		//emailService.issuedMedicineReservation(uid, rm.getPatient());		
	}

	@Override
	public List<ReservedMedicine> findFinishedReservationByPharmacy(Long id, Date startDate, Date endDate) {
		return mrRepository.findFinishedReservationByPharmacy(id,startDate,endDate);
	}

	@Override
	public List<ReservedMedicine> allfinishedReservation(Long id) {
		return mrRepository.findAllfinishedReservation(id);
	}

	@Override
	public List<ReservedMedicine> findReservationForMedicineAndPharmacyNotFinished(Long medicineId, Long pharmacyId) {
		return mrRepository.findReservationForMedicineAndPharmacyNotFinished(medicineId, pharmacyId);
	}
}
