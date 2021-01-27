package isa.apoteka.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pharmacyadmin")
public class PharmacyAdmin extends User{

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;

	public PharmacyAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PharmacyAdmin(Pharmacy pharmacy) {
		super();
		this.pharmacy = pharmacy;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}
	
	
}
