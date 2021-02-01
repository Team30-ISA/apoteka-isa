package isa.apoteka.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import isa.apoteka.domain.Gender;

public class NewPharmacistDTO {

	@Size(min=2, max=50, message = "First Name has to have min 2 letters and max 50.")
	private String firstName;
	@Size(min=2, max=50, message = "Last Name has to have min 2 letters and max 50.")
	private String lastName;
	@Size(min=2, max=50, message = "Username has to have min 2 letters and max 50.")
	private String username;
	@Email(message = "Email is not in the correct format.")
	private String email;
	@Size(min=2, max=50, message = "Address has to have min 2 letters and max 50.")
	private String address;
	@NotNull(message = "You have to choose a city.")
	private Long cityId;
	private Gender gender;
	public NewPharmacistDTO() {
		super();
	}
	public NewPharmacistDTO(String firstName, String lastName, String username, String email, String address,
			Long cityId, Gender gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.address = address;
		this.cityId = cityId;
		this.gender = gender;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	
	
	
}
