package isa.apoteka.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pharmacist")
public class Pharmacist extends User{

	private static final long serialVersionUID = -3373281718733941809L;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	
	private Date lastReqDate;
	
	public Pharmacist() {
		super();
		this.version = 0L;
	}
	
	public Pharmacist(Long id,String firstName, String lastName, Date lastReqDate) {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.lastReqDate = lastReqDate;
		this.version = 0L;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Date getLastReqDate() {
		return lastReqDate;
	}

	public void setLastReqDate(Date lastReqDate) {
		this.lastReqDate = lastReqDate;
	}
	
	
}
