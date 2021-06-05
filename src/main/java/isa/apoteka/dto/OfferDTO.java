package isa.apoteka.dto;

import isa.apoteka.domain.Offer;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;

public class OfferDTO {

    private Long errandId;
    
    @FutureOrPresent(message="Only date in future is accepted!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date supplyDeadline;

    private double price;

    private Boolean isApproved;

    public OfferDTO() {
    }

    public OfferDTO(Offer offer) {
        this.errandId = offer.getErrand().getId();
        this.supplyDeadline = offer.getSupplyDeadline();
        this.price = offer.getPrice();
        this.isApproved = offer.getIsApproved();
    }

    public Long getErrandId() {
        return errandId;
    }

    public void setErrandId(Long errandId) {
        this.errandId = errandId;
    }

    public Date getSupplyDeadline() {
        return supplyDeadline;
    }

    public void setSupplyDeadline(Date supplyDeadline) {
        this.supplyDeadline = supplyDeadline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}
