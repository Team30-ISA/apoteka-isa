package isa.apoteka.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="dermatologist")
public class Dermatologist extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy = "dermatologists")
	private List<Pharmacy> pharmacies;
	
	private int grade;
	

	public Dermatologist() {
		super();

	}
	
	public Dermatologist(Long id,String firstName, String lastName) {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
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
