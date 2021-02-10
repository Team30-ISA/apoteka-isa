package isa.apoteka.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private static final long serialVersionUID = 2354752262777558267L;

	
	@JsonIgnore
	@ManyToMany(mappedBy = "dermatologists",cascade = CascadeType.MERGE)
	private List<Pharmacy> pharmacies;
	
	private int grade;
	

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
	
	public int getGrade() {
		return this.grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
