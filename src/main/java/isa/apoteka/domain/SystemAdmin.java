package isa.apoteka.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "SYSTEM_ADMINS")
public class SystemAdmin extends User {
	
	public SystemAdmin() {
		
	}

	public SystemAdmin(Long id, String email, String password, String firstName, String lastName, String telephone) {
		super(id, email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}

	public SystemAdmin(String email, String password, String firstName, String lastName, String telephone) {
		super(email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}

	

	
}
