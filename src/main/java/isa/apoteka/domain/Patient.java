package isa.apoteka.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="patient")
public class Patient extends User{

	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy = "patients")
	private List<Pharmacy> pharmacies;
	
	@JsonIgnore
	@ManyToMany
	private List<Medicine> allergies;
	
	public Patient() {
		super();
	}

    public Patient(UserRequest userRequest) {
        super(userRequest);
    }
}
