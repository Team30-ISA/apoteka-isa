package isa.apoteka.dto;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class ChangeDataDTO {
	@Size(min=2, max=50)
	private String firstName;
	@Size(min=2, max=50)
	private String lastName;
	@Size(min=2, max=50)
	private String street;
	@NotNull
	private Long CityId;
	
	public ChangeDataDTO(String firstName, String lastName, String street, Long cityId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		CityId = cityId;
	}

	public ChangeDataDTO() {
		super();
		// TODO Auto-generated constructor stub
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
		return CityId;
	}

	public void setCityId(Long cityId) {
		CityId = cityId;
	}

	
	
}
