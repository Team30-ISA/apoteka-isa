package isa.apoteka.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="pharmacistWorkCalendar")
public class PharmacistWorkCalendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacist pharmacist;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	private Date startDate;
	private Date endDate;
	@Version
    private Long version;
	private Date lastReqDate;
	
	public PharmacistWorkCalendar() {
		super();
	}

	public PharmacistWorkCalendar(Pharmacist pharmacist, Pharmacy pharmacy, Date startDate, Date endDate, Date lastReqDate) {
		this.pharmacist = pharmacist;
		this.pharmacy = pharmacy;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastReqDate = lastReqDate;
	}

	public Long getId() {
		return id;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getLastReqDate() {
		return lastReqDate;
	}

	public void setLastReqDate(Date lastReqDate) {
		this.lastReqDate = lastReqDate;
	}
		
}
