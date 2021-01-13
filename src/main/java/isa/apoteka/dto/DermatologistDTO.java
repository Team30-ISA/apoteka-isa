package isa.apoteka.dto;

import isa.apoteka.domain.User;

public class DermatologistDTO extends UserDTO{

	public DermatologistDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DermatologistDTO(Long id, String firstName, String lastName) {
		super(id, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

	public DermatologistDTO(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}
	
	

}
