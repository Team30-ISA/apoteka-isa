package isa.apoteka.dto;

import isa.apoteka.domain.User;

public class PharmacistDTO extends UserDTO{

	public PharmacistDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PharmacistDTO(Long id, String firstName, String lastName) {
		super(id, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public PharmacistDTO(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

}
