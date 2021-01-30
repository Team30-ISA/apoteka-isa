package isa.apoteka.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="dermatologist")
public class Dermatologist extends User{
	@JsonIgnore
	@ManyToMany(mappedBy = "dermatologists")
	private List<Pharmacy> pharmacies;


	public Dermatologist() {
		super();
		// TODO Auto-generated constructor stub

	}

	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}


	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}
}
