package isa.apoteka.dto;

import isa.apoteka.domain.Pharmacy;

public class PharmacyDTO {
	private String name;

	public PharmacyDTO() {
		
	}

	public PharmacyDTO(String name) {
		this.name = name;
	}
	
	public PharmacyDTO(Pharmacy pharmacy) {
		this(pharmacy.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
