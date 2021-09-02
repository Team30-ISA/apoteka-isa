package isa.apoteka.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PatientUpdateForm {

	private Long id;
	
	private String username;

	private String oldPass;
	
	private String newPass;

	private String name;

	private String surname;
	
	private String email;
	
	private Address address;
	
	private String phonenumber;
	
	@Size(min=2, max=50)
	private String street;
	@NotNull
	private Long cityId;

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phoneNumber) {
		this.phonenumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldpass() {
		return oldPass;
	}
	public void setOldPass(String password) {
		this.oldPass = password;
	}

	public void setNewPass(String password) {
		this.newPass = password;
	}
	
	public String getNewPass() {
		return newPass;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstname) {
		this.name = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String lastname) {
		this.surname = lastname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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