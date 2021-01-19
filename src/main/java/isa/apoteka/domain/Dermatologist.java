package isa.apoteka.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="DERMATOLOGIST")
public class Dermatologist extends User{
	
	
	@ManyToMany(mappedBy = "dermatologists")
	private List<Pharmacy> pharmacies;
	
	public Dermatologist() {
	}
	
	
	public Dermatologist(Long id, String email, String password, String firstName, String lastName, String telephone) {
		super(id, email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}

	public Dermatologist(String email, String password, String firstName, String lastName, String telephone) {
		super(email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}






	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}

}
