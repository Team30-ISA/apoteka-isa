package isa.apoteka.dto;

import isa.apoteka.domain.Offer;

public class OfferPreviewDTO extends OfferDTO{
    private String pharmacy;

    public OfferPreviewDTO(Offer offer, String pharmacy) {
        super(offer);
        this.pharmacy = pharmacy;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }
}
