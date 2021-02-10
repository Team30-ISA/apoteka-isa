package isa.apoteka.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import isa.apoteka.dto.PharmacistDTO;

@Entity
@Table(name="pharmacyadmin")
public class PharmacyAdmin extends User {
	private static final long serialVersionUID = -2945161513410505916L;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
	

	public PharmacyAdmin() {
		super();
	}

	public PharmacyAdmin(Pharmacy pharmacy) {
		super();
		this.pharmacy = pharmacy;
	}

    public PharmacyAdmin(PharmacistDTO pharmacyAdminData, Pharmacy pharmacy) {
		super(pharmacyAdminData);
		this.pharmacy = pharmacy;
    }

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	@Override
	public Address getAddress() {
		return address;
	}
	@Override
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
