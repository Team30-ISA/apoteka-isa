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
	private LeaveRequestStatus status;
	
	public PharmacistHoliday() {
		super();
	}
	
	

	public PharmacistHoliday(Date startDate, Date endDate, Pharmacist pharmacist, LeaveRequestStatus status) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.pharmacist = pharmacist;
		this.status = status;
	}



	public PharmacistHoliday(Long id, Date startDate, Date endDate, Pharmacist pharmacist, LeaveRequestStatus status) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.pharmacist = pharmacist;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Pharmacist getPharmacist() {
		return pharmacist;
	}

	public void setPharmacist(Pharmacist pharmacist) {
		this.pharmacist = pharmacist;
	}

	public LeaveRequestStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveRequestStatus status) {
		this.status = status;
	}
	

}
