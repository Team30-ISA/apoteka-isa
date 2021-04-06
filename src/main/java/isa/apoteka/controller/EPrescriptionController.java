package isa.apoteka.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import isa.apoteka.domain.EPrescription;
import isa.apoteka.domain.MedicineEPrescription;
import isa.apoteka.domain.MedicinePrice;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.dto.ChoosenPharmacyDTO;
import isa.apoteka.dto.EPrescriptionAllInfoDTO;
import isa.apoteka.dto.EPrescriptionDTO;
import isa.apoteka.dto.PharmacyMedicineAvailabilityDTO;
import isa.apoteka.dto.QRcodeInformationDTO;
import isa.apoteka.repository.MedicineEPrescriptionRepository;
import isa.apoteka.service.EPrescriptionService;
import isa.apoteka.service.PharmacyGradeService;
import isa.apoteka.service.PharmacyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

@RestController
@RequestMapping(value = "/api/eprescription")
public class EPrescriptionController {
	
    private PharmacyService pharmacyService;
    private EPrescriptionService ePrescriptionService;
    private MedicineEPrescriptionRepository medicineEPrescriptionRepository;
    private PharmacyGradeService  pharmacyGradeService;
    
    @Autowired
    public EPrescriptionController(PharmacyService pharmacyService,
			EPrescriptionService ePrescriptionService,
			MedicineEPrescriptionRepository medicineEPrescriptionRepository, PharmacyGradeService pharmacyGradeService) {
		
		this.pharmacyService = pharmacyService;
		this.ePrescriptionService = ePrescriptionService;
		this.medicineEPrescriptionRepository = medicineEPrescriptionRepository;
		this.pharmacyGradeService = pharmacyGradeService;
	}

	
    

    @GetMapping("/all/{id}")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<List<EPrescriptionDTO>> getAllEPrescriptions(@PathVariable Long id) {
    	try {
    		List<MedicineEPrescription> medicineEPrescriptions = medicineEPrescriptionRepository.findAll();
            List<EPrescriptionDTO> ePrescriptionDTOS = new ArrayList<EPrescriptionDTO>();
            for(MedicineEPrescription medicineEPrescription: medicineEPrescriptions) {
                if (medicineEPrescription.getePrescription().getPatient().getId().equals(id)) {
                	EPrescriptionDTO ePrescriptionDTO = new EPrescriptionDTO();
                    ePrescriptionDTO.setDate(medicineEPrescription.getePrescription().getDate());
                    Pharmacy pharmacy = pharmacyService.findById(medicineEPrescription.getePrescription().getPharmacyId());
                    ePrescriptionDTO.setPharmacyName(pharmacy.getName());
                    ePrescriptionDTO.setMedicineName(medicineEPrescription.getName());
                    ePrescriptionDTO.setQuantity(medicineEPrescription.getQuantity());
                    ePrescriptionDTOS.add(ePrescriptionDTO);
                }
            }
            return ResponseEntity.ok(ePrescriptionDTOS);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
    
    @PostMapping("/choosePharmacy")
    @PreAuthorize("hasRole('PATIENT')")
    ResponseEntity<String> choosePharmacyForEReceipt(@RequestBody ChoosenPharmacyDTO choosenPharmacy) {
       
    	
    	if(!this.checkEprescription(choosenPharmacy)) {
            throw new IllegalArgumentException("Please fill in all the fields correctly!");
        }
        
        return ePrescriptionService.proccedEReceipt(choosenPharmacy) == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok("Successfully updated!");
    }
    
    private Boolean checkEprescription(ChoosenPharmacyDTO choosenPharmacy) {
        if(choosenPharmacy.getPharmacyId() <= 0 || !choosenPharmacy.getPharmacyId().equals(choosenPharmacy.getPharmacyId())) {
            return false;
        }
        if(choosenPharmacy.getMedications() == null) {
            return false;
        }
        return true;
    }
    
    @PostMapping("/uploadFile")
    ResponseEntity<EPrescriptionAllInfoDTO> uploadFile(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
            	
                BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                File destination = new File("src/main/resources/qrcode/" + file.getOriginalFilename());
                ImageIO.write(src, "png", destination);
                String decodedText = decodeQRCode(new File("src/main/resources/qrcode/" + file.getOriginalFilename()));
                
                if (decodedText == null) {
                    throw new IllegalArgumentException("Please upload correct QR code!");
                } else {
                    String code = getEPrescriptionCode(decodedText);
                    System.out.println("Ovo je code " + code);
                    EPrescription ePrescription = new EPrescription();
                    ePrescription = ePrescriptionService.findByCode(code);
                   
                    if(code == null) {
                    	System.out.println("Ima ga!");
                    }
                    try {
                    if(ePrescription != null) {
                        throw new IllegalArgumentException("This eReceipt is already used!");
                    }
                    }catch(NullPointerException e) {
            		}
                    
                    List<QRcodeInformationDTO> medicinesInQRcode = this.getMedicinesInQRcode(decodedText);
                    
                    try {
                    if(medicinesInQRcode.size() == 0) {
                        throw new IllegalArgumentException("Please try later!");
                    }
                    }catch (NullPointerException e) {	
					}
                    
                    List<PharmacyMedicineAvailabilityDTO> pharmacyAvailability = new ArrayList<>();
                	try {
                		pharmacyAvailability = this.getAvailabilityInPharmacies(medicinesInQRcode);
            		}
            		catch(NullPointerException e) {
            		}
                    EPrescriptionAllInfoDTO ePrescriptionFullInfoDTO = new EPrescriptionAllInfoDTO(pharmacyAvailability,medicinesInQRcode,code);
                    
                    try {
                    	return ResponseEntity.ok(ePrescriptionFullInfoDTO);
					} catch (Exception e) {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
                   

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
        List<QRcodeInformationDTO> qrList = new ArrayList<QRcodeInformationDTO>();
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
    	 List<PharmacyMedicineAvailabilityDTO> pharmacyList = new ArrayList<PharmacyMedicineAvailabilityDTO>();
         List<Pharmacy> pharmacies = pharmacyService.findAll();
         for(Pharmacy pharmacy : pharmacies) {
             double hasMedicationsPrice = pharmacyHasAllMedications(pharmacy.getMedicinePrices(),medicinesInQRcode);
             double grade = pharmacyGradeService.findGradeForPharmacy(pharmacy.getId());
			
             if(hasMedicationsPrice>0) {
                 pharmacyList.add(new PharmacyMedicineAvailabilityDTO(pharmacy.getId(), pharmacy.getName(), pharmacy.getCity(), pharmacy.getStreet(), hasMedicationsPrice, grade));
             }
         }
         return pharmacyList;
    }
    
    private double pharmacyHasAllMedications(List<MedicinePrice> medicinePrices, List<QRcodeInformationDTO> medicinesInQRcode) {
        double totalPrice = 0;
        for (QRcodeInformationDTO med : medicinesInQRcode) {
            double price = existsInPharmacy(medicinePrices,med);
            if(price < 0) {
            	return -1;
            	}
            else {
                totalPrice+=price;
            }
        }
        return totalPrice;
    }
    
    private double existsInPharmacy(List<MedicinePrice> medicinePrices, QRcodeInformationDTO medicine) {
        for (MedicinePrice medicinePrice : medicinePrices) {
            if(medicinePrice.getMedicine().getCode().equals(medicine.getMedicationCode()) &&
                    medicinePrice.getMedicine().getName().equals(medicine.getMedicationName())  &&
                    medicinePrice.getQuantity() > medicine.getQuantity()) {
                return (double)medicinePrice.getPrice()*medicine.getQuantity();
            }
        }
        return -1;
    }
 
}
