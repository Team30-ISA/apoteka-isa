package isa.apoteka.dto;

import isa.apoteka.domain.Address;

public class PharmacyMedicineAvailabilityDTO {
	private Long pharmacyId;
    private double sumPrice;
    private double mark;
    private Address address;
    private String pharmacyName;

    public PharmacyMedicineAvailabilityDTO() { }

    public PharmacyMedicineAvailabilityDTO(Long pharmacyId, double sumPrice, double mark, Address address, String pharmacyName) {
        this.pharmacyId = pharmacyId;
        this.sumPrice = sumPrice;
        this.mark = mark;
        this.address = address;
        this.pharmacyName = pharmacyName;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmId) {
        pharmacyId = pharmId;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}
