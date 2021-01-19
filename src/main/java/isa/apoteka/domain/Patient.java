package isa.apoteka.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "PATIENTS")
public class Patient extends User {

	
	
	public Patient() {
		super();
	}

	public Patient(String email, String password, String firstName, String lastName, String telephone, Country country, City city, Address address) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.country = country;
		this.city = city;
		this.address = address;
	}

	
}
