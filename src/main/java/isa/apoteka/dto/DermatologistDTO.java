package isa.apoteka.dto;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.User;

public class DermatologistDTO extends UserDTO{

	public DermatologistDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DermatologistDTO(Long id, String username, String firstName, String lastName, String email, Address address, String phonenumber) {
		super(id, username, firstName, lastName, email, address, phonenumber);
		// TODO Auto-generated constructor stub
	}

	public DermatologistDTO(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}
	
	

}
