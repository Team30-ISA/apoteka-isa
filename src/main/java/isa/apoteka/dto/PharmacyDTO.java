package isa.apoteka.dto;

import isa.apoteka.domain.Pharmacy;

public class PharmacyDTO {
	private Long id;
	private String name;
	private String address;
	private String city;

	public PharmacyDTO() {
		
	}


	
	public PharmacyDTO(Pharmacy pharmacy) {
		this(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress().getStreet(), pharmacy.getAddress().getCity().getCity());
	}



	public PharmacyDTO(Long id, String name, String address, String city) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
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

	
	
	
}
