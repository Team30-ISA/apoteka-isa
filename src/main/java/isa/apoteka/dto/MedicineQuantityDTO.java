package isa.apoteka.dto;

public class MedicineQuantityDTO {
	private Long id;
	private Long errandId;
	private String name;
	private int quantity;

	public MedicineQuantityDTO() {
		super();
	}

	public MedicineQuantityDTO(Long id, Long errandId, String name, int quantity) {
		super();
		this.id = id;
		this.errandId = errandId;
		this.name = name;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getErrandId() {
		return errandId;
	}

	public void setErrandId(Long errandId) {
		this.errandId = errandId;
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
