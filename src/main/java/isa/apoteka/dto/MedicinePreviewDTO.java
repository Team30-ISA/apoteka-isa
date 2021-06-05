package isa.apoteka.dto;

import isa.apoteka.domain.DrugForm;
import isa.apoteka.domain.DrugType;
import isa.apoteka.domain.Medicine;
import isa.apoteka.domain.Pharmacy;

public class MedicinePreviewDTO {
    private MedicineDTO medicine;
    private PharmacyDTO pharmacy;

    public MedicinePreviewDTO() {
    }

    public MedicinePreviewDTO(Medicine medicine, Pharmacy pharmacy, int price, int quantity, DrugType type, DrugForm form) {
        this.medicine = new MedicineDTO(medicine, quantity, price, type, form);
        this.pharmacy = new PharmacyDTO(pharmacy);
    }

    public MedicineDTO getMedicine() {
        return medicine;
    }

    public void setMedicine(MedicineDTO medicineDTO) {
        this.medicine = medicineDTO;
    }

    public PharmacyDTO getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(PharmacyDTO pharmacyDTO) {
        this.pharmacy = pharmacyDTO;
    }
}
