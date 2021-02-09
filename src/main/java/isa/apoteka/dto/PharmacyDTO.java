package isa.apoteka.dto;

import javax.validation.constraints.Size;

import isa.apoteka.domain.Pharmacy;

public class PharmacyDTO {
	private Long id;
	@Size(min=2, max=50, message="Name has to have at least 2 characters.")
	private String name;
	@Size(min=2, max=50, message="Address has to have at least 2 characters.")
	private String address;
	@Size(min=2, max=50, message="City has to have at least 2 characters.")
	private String city;
	private String description;
	private double grade;

	public PharmacyDTO() {
		
	}


	
	public PharmacyDTO(Pharmacy pharmacy) {
		this(pharmacy.getId(), pharmacy.getName(), pharmacy.getStreet(), pharmacy.getCity(), pharmacy.getDescription());
	}



	public PharmacyDTO(Long id, String name, String address, String city, String description) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.description = description;
	}
	
	public PharmacyDTO(Long id, String name, String address, String city, String description, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.description = description;
		this.grade = grade;
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



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getGrade() {
		return grade;
	}



	public void setGrade(double grade) {
		this.grade = grade;
	}

	
	
	
}
