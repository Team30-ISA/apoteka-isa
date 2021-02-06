package isa.apoteka.dto;

public class MedicineNameDTO {
	private Long id;
	private String name;
	
	public MedicineNameDTO() {
		super();
	}
	public MedicineNameDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
	

}
