package isa.apoteka.domain;


import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "PHARMACY_ADMINS")
public class PharmacyAdmin extends User {
	
	public PharmacyAdmin() {
		
	}

	public PharmacyAdmin(Long id, String email, String password, String firstName, String lastName, String telephone) {
		super(id, email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}

	public PharmacyAdmin(String email, String password, String firstName, String lastName, String telephone) {
		super(email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}
	
	

		
}
