package isa.apoteka.dto;

import isa.apoteka.domain.User;

public class UserDTO {
	private Long id;
	private String firstName;
	private String lastName;
	
	public UserDTO() {

	}

	public UserDTO(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserDTO(User user) {
		this(user.getId(), user.getFirstName(), user.getLastName());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}
