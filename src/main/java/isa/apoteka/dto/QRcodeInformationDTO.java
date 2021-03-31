package isa.apoteka.dto;

public class QRcodeInformationDTO {
    private String medicationName;
    private Long medicationCode;
    private int quantity;

    public QRcodeInformationDTO() {}

    public QRcodeInformationDTO(String medicationName, long medicationCode, int quantity) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
