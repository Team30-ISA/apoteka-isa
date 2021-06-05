package isa.apoteka.dto;

public class QRcodeInformationDTO {
    private String medicationName;
    private long medicationCode;
    private Integer quantity;

    public QRcodeInformationDTO() {}

    public QRcodeInformationDTO(String medicationName, long medicationCode, Integer quantity) {
        this.medicationName = medicationName;
        this.medicationCode = medicationCode;
        this.quantity = quantity;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public long getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(long medicationCode) {
        this.medicationCode = medicationCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
