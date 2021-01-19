package isa.apoteka.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "SUPPLIERS")
public class Supplier extends User {

	
	public Supplier() {

	}

	public Supplier(Long id, String email, String password, String firstName, String lastName, String telephone) {
		super(id, email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}

	public Supplier(String email, String password, String firstName, String lastName, String telephone) {
		super(email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}

	

}
