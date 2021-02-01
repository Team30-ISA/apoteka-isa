package isa.apoteka.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ChangeDataDTO {
	@Size(min=2, max=50)
	private String firstName;
	@Size(min=2, max=50)
	private String lastName;
	@Size(min=2, max=50)
	private String street;
	@NotNull
	private Long cityId;
	
	public ChangeDataDTO(String firstName, String lastName, String street, Long cityId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.cityId = cityId;
	}

	public ChangeDataDTO() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	
	
}
