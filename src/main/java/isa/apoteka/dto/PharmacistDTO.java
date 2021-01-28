package isa.apoteka.dto;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.User;

public class PharmacistDTO extends UserDTO{

	public PharmacistDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PharmacistDTO(Long id, String username, String firstName, String lastName, String email, Address address, String phonenumber) {
		super(id, username, firstName, lastName, email, address, phonenumber);
		// TODO Auto-generated constructor stub
	}

	public PharmacistDTO(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

}
