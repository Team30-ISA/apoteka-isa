package isa.apoteka.dto;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.User;

public class DermatologistDTO extends UserDTO{

	public DermatologistDTO() {
		super();
	}

	public DermatologistDTO(Long id, String username, String firstName, String lastName, String email, Address address, String phonenumber) {
		super(id, username, firstName, lastName, email, address, phonenumber);
	}

	public DermatologistDTO(User user) {
		super(user);
	}
	
	

}
