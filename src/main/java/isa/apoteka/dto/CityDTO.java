package isa.apoteka.dto;

import isa.apoteka.domain.City;
import isa.apoteka.domain.Country;

public class CityDTO {
	private Long id;
	private String city;
	private Country country;
	
	public CityDTO() {

	}

	public CityDTO(Long id, String city, Country country) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
	}
	
	public CityDTO(City city) {
		this(city.getId(), city.getCity(), city.getCountry());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
