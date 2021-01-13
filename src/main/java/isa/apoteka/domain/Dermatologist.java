package isa.apoteka.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="dermatologist")
public class Dermatologist extends User{
	
	@ManyToMany(mappedBy = "dermatologists")
	private List<Pharmacy> pharmacies;


	public Dermatologist() {
		super();
		// TODO Auto-generated constructor stub

	}
	

}
