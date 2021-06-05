package isa.apoteka.domain;

public class MedicineDisplay {
	private Long id;
	private String name;
	private int quantity;
	
	public MedicineDisplay() {
		
	}

	public MedicineDisplay(Long id, String Name, int quantity) {
		this.id = id;
		this.name = Name;
		this.quantity = quantity;
	}
	
	public Long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
