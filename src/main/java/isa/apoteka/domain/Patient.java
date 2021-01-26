package isa.apoteka.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class Patient extends User{

	private static final long serialVersionUID = 1L;

	public Patient() {
		super();
	}

}
