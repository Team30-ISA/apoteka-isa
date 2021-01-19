package isa.apoteka.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PHARMACIST")
public class Pharmacist extends User{

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	
	
	
	@ManyToMany(mappedBy = "dermatologists")
	private List<Pharmacy> pharmacies;
	
	public Pharmacist() {

	}

	public Pharmacist(Long id, String email, String password, String firstName, String lastName, String telephone) {
		super(id, email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}

	public Pharmacist(String email, String password, String firstName, String lastName, String telephone) {
		super(email, password, firstName, lastName, telephone);
		// TODO Auto-generated constructor stub
	}






	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}

	
}
