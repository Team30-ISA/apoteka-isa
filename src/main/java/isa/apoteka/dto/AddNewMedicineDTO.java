package isa.apoteka.dto;

public class AddNewMedicineDTO {
	private Long id;

	public AddNewMedicineDTO() {
		super();
	}

	public AddNewMedicineDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
