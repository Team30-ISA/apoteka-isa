package isa.apoteka.dto;

public class MedicineQuantityPreviewDTO {
    private Long medicineId;
    private String medicine;
    private Integer quantity;

    public MedicineQuantityPreviewDTO() {
    }

    public MedicineQuantityPreviewDTO(Long medicineId, String medicine, Integer quantity) {
        this.medicineId = medicineId;
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }
}
