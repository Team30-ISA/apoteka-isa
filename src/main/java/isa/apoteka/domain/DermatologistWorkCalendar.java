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
@Table(name="dermatologistWorkCalendar")
public class DermatologistWorkCalendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Dermatologist dermatologist;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pharmacy pharmacy;
	private Date startDate;
	private Date endDate;
	@Version
    private Long version;
	private Date lastReqDate;
	
	public DermatologistWorkCalendar() {
		super();
		this.version = 0L;
	}

	public DermatologistWorkCalendar(Dermatologist dermatologist, Pharmacy pharmacy, Date startDate,
			Date endDate, Date lastReqDate) {
		this.dermatologist = dermatologist;
		this.pharmacy = pharmacy;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastReqDate = lastReqDate;
		this.version = 0L;
	}

	public Long getId() {
		return id;
	}

	public Dermatologist getDermatologist() {
		return dermatologist;
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

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
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
