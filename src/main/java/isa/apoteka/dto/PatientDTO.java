package isa.apoteka.dto;

import isa.apoteka.domain.User;

public class PatientDTO extends UserDTO{

	public PatientDTO() {
		super();
	}

	public PatientDTO(Long id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

	public PatientDTO(User user) {
		super(user);
	}
	
}
