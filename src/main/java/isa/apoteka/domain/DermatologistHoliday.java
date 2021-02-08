package isa.apoteka.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DermatologistHoliday")
public class DermatologistHoliday {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date startDate;
	private Date endDate;
	@ManyToOne
	private Dermatologist dermatologist;
	private LeaveRequestStatus status;
	public DermatologistHoliday() {
		super();
	}
	public DermatologistHoliday(Long id, Date startDate, Date endDate, Dermatologist dermatologist, 
			LeaveRequestStatus status) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dermatologist = dermatologist;
		this.status = status;
	}
	public DermatologistHoliday(Date startDate, Date endDate, Dermatologist dermatologist, 
			LeaveRequestStatus status) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.dermatologist = dermatologist;
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
	public Dermatologist getDermatologist() {
		return dermatologist;
	}
	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}
	public LeaveRequestStatus getStatus() {
		return status;
	}
	public void setStatus(LeaveRequestStatus status) {
		this.status = status;
	}
	


}
