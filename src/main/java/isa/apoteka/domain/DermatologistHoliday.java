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
	private Boolean approved;
	
	public DermatologistHoliday() {
		super();
	}
	
	public DermatologistHoliday(Date start, Date end, Dermatologist dermatologist) {
		this.startDate = start;
		this.endDate = end;
		this.dermatologist = dermatologist;
		this.approved = false;
	}

	public DermatologistHoliday(Long id, Date start, Date end, Dermatologist dermatologist) {
		this.id = id;
		this.startDate = start;
		this.endDate = end;
		this.dermatologist = dermatologist;
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

	public Dermatologist getDermatologist() {
		return dermatologist;
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

	public void setDermatologist(Dermatologist dermatologist) {
		this.dermatologist = dermatologist;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

}
