package isa.apoteka.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import isa.apoteka.dto.PharmacistDTO;

@Entity
@Table(name="dermatologist")
public class Dermatologist extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy = "dermatologists")
	private List<Pharmacy> pharmacies;

	

	public Dermatologist() {
		super();

	}

    public Dermatologist(PharmacistDTO dermatologistData) {
		super(dermatologistData);
    }

    public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}


	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}
}
