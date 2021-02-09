package isa.apoteka.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="patient")
public class Patient extends User{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToMany(mappedBy = "patients")
	private List<Pharmacy> pharmacies;
	
	//@OneToMany(mappedBy = "patients")
	//private List<Medicine> reservedMedications;
	@OneToMany(mappedBy = "medicine")
	private List<ReservedMedicine> reservedMedicine;

	@JsonIgnore
	@ManyToMany
	private List<Medicine> allergies;

	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}

	public Patient() {
		super();
	}

    public Patient(UserRequest userRequest) {
        super(userRequest);
    }
}
