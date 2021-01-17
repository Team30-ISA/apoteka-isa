package isa.apoteka.dto;

import isa.apoteka.domain.Pharmacy;

public class PharmacyDTO {
	private Long id;
	private String name;
	private String address;

	public PharmacyDTO() {
		
	}

	public PharmacyDTO(Long id, String name, String address) {
		this.name = name;
		this.id = id;
		this.address = address;
	}
	
	public PharmacyDTO(Pharmacy pharmacy) {
		this(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}