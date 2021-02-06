package isa.apoteka.dto;

public class MedicineForSupplyDTO {
	private Long medicineId;
	private String name;
	private int quantity;
	public MedicineForSupplyDTO() {
		super();
	}
	public MedicineForSupplyDTO(Long medicineId, String name, int quantity) {
		super();
		this.medicineId = medicineId;
		this.name = name;
		this.quantity = quantity;
	}
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
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
	
	
}
