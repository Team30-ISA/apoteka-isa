package isa.apoteka.service.impl;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.EPrescription;
import isa.apoteka.domain.MedicineEPrescription;
import isa.apoteka.domain.Patient;
import isa.apoteka.dto.ChoosenPharmacyDTO;
import isa.apoteka.dto.QRcodeInformationDTO;
import isa.apoteka.repository.EPrescriptionRepository;
import isa.apoteka.repository.MedicineEPrescriptionRepository;
import isa.apoteka.service.EPrescriptionService;
import isa.apoteka.service.MedicinePriceService;

@Service
public class EPrescriptionServiceImpl implements EPrescriptionService {
	
    private EPrescriptionRepository ePrescriptionRepository;
    private MedicineEPrescriptionRepository medicationEPrescriptionRepository;
    private MedicinePriceService medicationPriceService;
    private EmailService emailService;
   
    @Autowired
	public EPrescriptionServiceImpl(EPrescriptionRepository ePrescriptionRepository,
			MedicineEPrescriptionRepository medicationEPrescriptionRepository,
			MedicinePriceService medicationPriceService, EmailService emailService) {
		this.ePrescriptionRepository = ePrescriptionRepository;
		this.medicationEPrescriptionRepository = medicationEPrescriptionRepository;
		this.medicationPriceService = medicationPriceService;
		this.emailService = emailService;
	}

	@Override
	public EPrescription findById(Long id) {
		return ePrescriptionRepository.getOne(id);
	}

	@Override
	public List<EPrescription> findAll() {
		return ePrescriptionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public EPrescription save(ChoosenPharmacyDTO choosenPharmacy) {
		try {
			Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            EPrescription ePrescription = new EPrescription();
            ePrescription.setPatient(patient);
            ePrescription.setDate(new Date());
            ePrescription.setCode(choosenPharmacy.getCode());
            ePrescription.setPharmacyId(choosenPharmacy.getPharmacyId());
            EPrescription ePrescription1 = ePrescriptionRepository.save(ePrescription);
            List<QRcodeInformationDTO> qRcodeInformationDTOS = choosenPharmacy.getMedications();
            for (QRcodeInformationDTO medication : qRcodeInformationDTOS) {
                MedicineEPrescription medicationEPrescription = new MedicineEPrescription();
                medicationEPrescription.setCode(medication.getMedicationCode());
                medicationEPrescription.setName(medication.getMedicationName());
                medicationEPrescription.setQuantity(medication.getQuantity());
                medicationEPrescription.setePrescription(ePrescription1);
                medicationEPrescriptionRepository.save(medicationEPrescription);
            }
            return ePrescription1;
        }
        catch (Exception e) {
            return null;
        }
	}

	@Transactional(readOnly = false)
    public Boolean proccedEReceipt(ChoosenPharmacyDTO choosenPharmacy) {
          return medicationPriceService.updateMedicineQuantityEreceipt(choosenPharmacy).equals(false) ||
                emailService.informPatientAboutEreceipt(choosenPharmacy.getMedications()).equals(false) ||
                save(choosenPharmacy) == null ?
                 true : false;
    }

    public EPrescription findByCode(String code) {
        return ePrescriptionRepository.findByCode(code);
    }
}
