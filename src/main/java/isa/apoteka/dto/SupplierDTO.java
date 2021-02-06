package isa.apoteka.dto;

import java.util.Date;

public class SupplierDTO {
	private Long supplierId;
	private Long offerId;
	private String firstName;
	private String lastName;
	private double price;
	private Date supplyDeadline;
	private Boolean approved;
	public SupplierDTO() {
		super();
	}
	public SupplierDTO(Long supplierId, Long offerId, String firstName, String lastName, double price,
			Date supplyDeadline, Boolean approved) {
		super();
		this.supplierId = supplierId;
		this.offerId = offerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.price = price;
		this.supplyDeadline = supplyDeadline;
		this.approved = approved;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public Long getOfferId() {
		return offerId;
	}
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Boolean getApproved() {
		return approved;
	}
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}
	
	
	
	
}
