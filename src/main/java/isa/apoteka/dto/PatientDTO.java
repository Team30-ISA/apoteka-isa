package isa.apoteka.dto;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.Gender;
import isa.apoteka.domain.User;

public class PatientDTO extends UserDTO{

	public PatientDTO() {
		super();
	}

	public PatientDTO(Long id, Gender gender, String username, String firstName, String lastName, String email, Address address, String phonenumber) {
		super(id, gender, username, firstName, lastName, email, address, phonenumber);
	}

	public PatientDTO(User user) {
		super(user);
	}
	
}
