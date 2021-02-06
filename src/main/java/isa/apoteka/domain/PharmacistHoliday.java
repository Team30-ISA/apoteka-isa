package isa.apoteka.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PharmacistHoliday")
public class PharmacistHoliday {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date startDate;
	private Date endDate;
	@ManyToOne
	private Pharmacist pharmacist;
	private Boolean approved;
	
	public PharmacistHoliday() {
		super();
	}
	
	public PharmacistHoliday(Date startDate, Date endDate, Pharmacist pharmacist) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.pharmacist = pharmacist;
		this.approved = false;
	}

	public PharmacistHoliday(Long id, Date startDate, Date endDate, Pharmacist pharmacist) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pharmacist = pharmacist;
		this.approved = false;
	}

	public Long getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}	

}
