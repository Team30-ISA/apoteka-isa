package isa.apoteka.dto;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.Gender;
import isa.apoteka.domain.User;

public class UserDTO {
	private Long id;
	private Gender gender;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private String phonenumber;
	
	public UserDTO() {

	}

	public UserDTO(Long id, Gender gender, String username, String firstName, String lastName, String email, Address address, String phonenumber) {
		super();
		this.id = id;
		this.gender = gender;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phonenumber = phonenumber;
	}
	
	public UserDTO(User user) {
		this(user.getId(), user.getGender(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), user.getPhonenumber());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
