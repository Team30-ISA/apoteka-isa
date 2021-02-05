package isa.apoteka.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class Patient extends User{

	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy = "patients")
	private List<Pharmacy> pharmacies;
	
	//@OneToMany(mappedBy = "patients")
	//private List<Medicine> reservedMedications;
	@OneToMany(mappedBy = "medicine")
	private List<ReservedMedicine> reservedMedicine;
	
	public Patient() {
		super();
	}

}
