package isa.apoteka.dto;

import java.util.List;

public class ChoosenPharmacyDTO {
    private Long pharmacyId;
    private List<QRcodeInformationDTO> medications;
    private String code;

    public ChoosenPharmacyDTO() {}

    public ChoosenPharmacyDTO(Long pharmacyId, List<QRcodeInformationDTO> medications, String code) {
        this.pharmacyId = pharmacyId;
        this.medications = medications;
        this.code = code;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public List<QRcodeInformationDTO> getMedications() {
        return medications;
    }

    public void setMedications(List<QRcodeInformationDTO> medications) {
        this.medications = medications;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
