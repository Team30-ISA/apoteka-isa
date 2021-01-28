package isa.apoteka.dto;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;
import isa.apoteka.domain.User;

public class CountryDTO {
	private Long id;
	private String country;
	
	public CountryDTO() {

	}

	public CountryDTO(Long id, String country) {
		super();
		this.id = id;
		this.country = country;
	}
	
	public CountryDTO(Country country) {
		this(country.getId(), country.getCountry());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
