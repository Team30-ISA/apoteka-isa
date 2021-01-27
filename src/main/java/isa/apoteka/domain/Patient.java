package isa.apoteka.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class Patient extends User{

	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy = "patients")
	private List<Pharmacy> pharmacies;
	
	public Patient() {
		super();
	}

}
