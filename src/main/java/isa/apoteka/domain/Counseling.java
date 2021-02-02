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

@Entity
@Table(name="counseling")
public class Counseling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date startDate;
	private int duration;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DermatologistWorkCalendar dermatologistWorkCalendar;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patient;
	private Float price;
	
	public Counseling() {
		super();
	}

	public Counseling(Long id, Date startDate, int duration, DermatologistWorkCalendar dermatologistWorkCalendar,
			Patient patient, Float price) {
		this.id = id;
		this.startDate = startDate;
		this.duration = duration;
		this.dermatologistWorkCalendar = dermatologistWorkCalendar;
		this.patient = patient;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public int getDuration() {
		return duration;
	}

	public DermatologistWorkCalendar getDermatologistWorkCalendar() {
		return dermatologistWorkCalendar;
	}

	public Patient getPatient() {
		return patient;
	}

	public Float getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setDermatologistWorkCalendar(DermatologistWorkCalendar dermatologistWorkCalendar) {
		this.dermatologistWorkCalendar = dermatologistWorkCalendar;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	
}
