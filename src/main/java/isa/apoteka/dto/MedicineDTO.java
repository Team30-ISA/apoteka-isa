package isa.apoteka.dto;

import java.util.Date;

public class MedicineDTO {
	private Long id;
	private String name;
	private int quantity;
	private int price;
	private Date startOfPrice;
	private Date endOfPrice;
	public MedicineDTO() {
		super();
	}
	public MedicineDTO(Long id, String name, int quantity, int price, Date startOfPrice, Date endOfPrice) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.startOfPrice = startOfPrice;
		this.endOfPrice = endOfPrice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getStartOfPrice() {
		return startOfPrice;
	}
	public void setStartOfPrice(Date startOfPrice) {
		this.startOfPrice = startOfPrice;
	}
	public Date getEndOfPrice() {
		return endOfPrice;
	}
	public void setEndOfPrice(Date endOfPrice) {
		this.endOfPrice = endOfPrice;
	}
	
	
	
	
	
}
