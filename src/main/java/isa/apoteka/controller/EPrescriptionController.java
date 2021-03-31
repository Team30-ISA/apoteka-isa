package isa.apoteka.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import isa.apoteka.domain.EPrescription;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.EPrescriptionAllInfoDTO;
import isa.apoteka.dto.PharmacyMedicineAvailabilityDTO;
import isa.apoteka.dto.QRcodeInformationDTO;
import isa.apoteka.repository.LoyaltyProgramRepository;
import isa.apoteka.repository.MedicineEPrescriptionRepository;
import isa.apoteka.service.EPrescriptionService;
import isa.apoteka.service.MedicinePriceService;
import isa.apoteka.service.PatientService;
import isa.apoteka.service.PharmacyService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/eprescription")
public class EPrescriptionController {
	
    private PharmacyService pharmacyService;
    private MedicinePriceService medicinePriceService;
    private PatientService patientService;
    private EPrescriptionService ePrescriptionService;
    private LoyaltyProgramRepository loyaltyProgramRepository;
    private MedicineEPrescriptionRepository medicineEPrescriptionRepository;
    
    @Autowired
    public EPrescriptionController(PharmacyService pharmacyService, MedicinePriceService medicinePriceService,
			PatientService patientService, EPrescriptionService ePrescriptionService,
			LoyaltyProgramRepository loyaltyProgramRepository,
			MedicineEPrescriptionRepository medicineEPrescriptionRepository) {
		this.pharmacyService = pharmacyService;
		this.medicinePriceService = medicinePriceService;
		this.patientService = patientService;
		this.ePrescriptionService = ePrescriptionService;
		this.loyaltyProgramRepository = loyaltyProgramRepository;
		this.medicineEPrescriptionRepository = medicineEPrescriptionRepository;
	}

    
    @PostMapping("/uploadFile")
    //@PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<EPrescriptionAllInfoDTO> uploadFile(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                File destination = new File("src/main/resources/qrcode/" + file.getOriginalFilename());
                ImageIO.write(src, "png", destination);
                String decodedText = decodeQRCode(new File("src/main/resources/qrcode/" + file.getOriginalFilename()));
                System.out.println(decodedText);
                if (decodedText.equals(null)) {
                    throw new IllegalArgumentException("Please upload correct QR code!");
                } else {
                    String code = getEPrescriptionCode(decodedText);
                    EPrescription ePrescription = ePrescriptionService.findByCode(code);
                    if(!ePrescription.equals(null)) {
                        throw new IllegalArgumentException("This eReceipt is already used!");
                    }
                    List<QRcodeInformationDTO> medicinesInQRcode = getMedicinesInQRcode(decodedText);
                    if(medicinesInQRcode.equals(null)) {
                        throw new IllegalArgumentException("Please try later!");
                    }
                    List<PharmacyMedicineAvailabilityDTO> pharmacyAvailability = getAvailabilityInPharmacies(medicinesInQRcode);
                    EPrescriptionAllInfoDTO ePrescriptionFullInfoDTO = new EPrescriptionAllInfoDTO(pharmacyAvailability,medicinesInQRcode,code);
                    return pharmacyAvailability.equals(null) ?
                            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                            ResponseEntity.ok(ePrescriptionFullInfoDTO);

                }
            } catch (IOException | NotFoundException e) {
                throw new IllegalArgumentException("Please upload correct QR code!");}
        }
        throw new IllegalArgumentException("Please upload correct QR code!");
    }
    
    private static String decodeQRCode(File qrCodeimage) throws IOException, NotFoundException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException n) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }
    
    private String getEPrescriptionCode(String decodedText) {
        String []code = decodedText.split("!");
        return code[0];
    }
    
    private List<QRcodeInformationDTO> getMedicinesInQRcode(String decodedText) {
        List<QRcodeInformationDTO> qrList = new ArrayList<>();
        if(decodedText.contains(";")) {
            String []code = decodedText.split("!");
            String []medications = code[1].split(";");
            for (String medication: medications) {
                String []medicationParts = medication.split("_");
                qrList.add(new QRcodeInformationDTO(medicationParts[0],Long.parseLong(medicationParts[1]),Integer.parseInt(medicationParts[2])));
            }
        }
        else {
            String []code = decodedText.split("!");
            String []medicationParts = code[1].split("_");
            qrList.add(new QRcodeInformationDTO(medicationParts[0],Long.parseLong(medicationParts[1]),Integer.parseInt(medicationParts[2])));
        }
        return qrList;
    }
    
    private List<PharmacyMedicineAvailabilityDTO> getAvailabilityInPharmacies(List<QRcodeInformationDTO> medicinesInQRcode) {
        List<PharmacyMedicineAvailabilityDTO> pharmacyList = new ArrayList<>();
      /*  List<Pharmacy> pharmacies = pharmacyService.findAll();
        for(Pharmacy pharmacy : pharmacies) {
            double hasMedicationsPrice = pharmacyHasAllMedications(pharmacy.get,medicationsInQRcode);
           // double priceWithLoyaltyProgram = setPriceWithLoyaltyProgram(hasMedicationsPrice);
            if(hasMedicationsPrice>0) {
                pharmacyList.add(new PharmacyMedicationAvailabilityDTO(pharmacy.getId(), priceWithLoyaltyProgram, pharmacy.getMark(),
                        new AddressDTO(pharmacy.getAddress().getTown(), pharmacy.getAddress().getStreet(), pharmacy.getAddress().getNumber(),
                                pharmacy.getAddress().getPostalCode(), pharmacy.getAddress().getCountry()), pharmacy.getPharmacyName()));
            }
        }*/
        return pharmacyList;
    }
}
