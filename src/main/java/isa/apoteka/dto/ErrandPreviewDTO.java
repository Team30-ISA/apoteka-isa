package isa.apoteka.dto;

import isa.apoteka.domain.Errand;
import isa.apoteka.domain.Pharmacy;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ErrandPreviewDTO {
    private Long id;

    private Date deadline;

    private Date creationTime;

    private String pharmacy;

    private List<MedicineQuantityPreviewDTO> medicineQuantity;

    public ErrandPreviewDTO() {
    }

    public ErrandPreviewDTO(Errand e) {
        this.id = e.getId();
        this.deadline = e.getDeadline();
        this.creationTime = e.getCreationTime();
        this.pharmacy = e.getPharmacy().getName();
        this.medicineQuantity = e.getMedicineForOrder().stream().map(m -> new MedicineQuantityPreviewDTO(m.getMedicine().getId(),m.getMedicine().getName(), m.getQuantity())).collect(Collectors.toList());
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    public List<MedicineQuantityPreviewDTO> getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(List<MedicineQuantityPreviewDTO> medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
