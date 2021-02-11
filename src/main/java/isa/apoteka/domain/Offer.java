package isa.apoteka.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Offer {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	
	@ManyToOne
	private Errand errand;
	
    @ManyToOne
    private Supplier supplier;

	private double price;
	
	private Date supplyDeadline;
	
	private Boolean isApproved;

	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Offer(Long id, Errand errand, Supplier supplier, double price, Date supplyDeadline, Boolean approved) {
		super();
		this.id = id;
		this.errand = errand;
		this.supplier = supplier;
		this.price = price;
		this.supplyDeadline = supplyDeadline;
		this.isApproved = approved;
	}

    public Offer(Errand errand, Supplier supplier, double price, Date supplyDeadline) {
		this.errand = errand;
		this.supplier = supplier;
		this.price = price;
		this.supplyDeadline = supplyDeadline;
		this.isApproved = null;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Errand getErrand() {
		return errand;
	}

	public void setErrand(Errand errand) {
		this.errand = errand;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getSupplyDeadline() {
		return supplyDeadline;
	}

	public void setSupplyDeadline(Date supplyDeadline) {
		this.supplyDeadline = supplyDeadline;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean approved) {
		this.isApproved = approved;
	}


	
	
	

}
