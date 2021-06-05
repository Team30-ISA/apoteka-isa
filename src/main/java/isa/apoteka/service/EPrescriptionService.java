package isa.apoteka.service;

import java.util.List;

import isa.apoteka.domain.EPrescription;
import isa.apoteka.dto.ChoosenPharmacyDTO;

public interface EPrescriptionService {
	
	EPrescription findById(Long id);
    List<EPrescription> findAll ();
    EPrescription save(ChoosenPharmacyDTO choosenPharmacy);
    Boolean proccedEReceipt(ChoosenPharmacyDTO choosenPharmacy);
    EPrescription findByCode(String code);
}
