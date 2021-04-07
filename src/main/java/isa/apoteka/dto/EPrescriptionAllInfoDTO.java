package isa.apoteka.dto;

import java.util.List;

public class EPrescriptionAllInfoDTO {
	private List<PharmacyMedicineAvailabilityDTO> pharmacies;
    private List<QRcodeInformationDTO> medicationsInQRcode;
    private String code;

    public EPrescriptionAllInfoDTO() {
    }

    public EPrescriptionAllInfoDTO(List<PharmacyMedicineAvailabilityDTO> pharmacies, List<QRcodeInformationDTO> medicationsInQRcode, String code) {
        this.pharmacies = pharmacies;
        this.medicationsInQRcode = medicationsInQRcode;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<PharmacyMedicineAvailabilityDTO> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyMedicineAvailabilityDTO> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public List<QRcodeInformationDTO> getMedicationsInQRcode() {
        return medicationsInQRcode;
    }

    public void setMedicationsInQRcode(List<QRcodeInformationDTO> medicationsInQRcode) {
        this.medicationsInQRcode = medicationsInQRcode;
    }
}
