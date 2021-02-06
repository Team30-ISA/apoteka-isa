package isa.apoteka.dto;

import java.util.Date;

public class SupplierDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private double price;
	private Date supplyDeadline;
	public SupplierDTO() {
		super();
	}
	public SupplierDTO(Long id, String firstName, String lastName, double price, Date supplyDeadline) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.price = price;
		this.supplyDeadline = supplyDeadline;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
