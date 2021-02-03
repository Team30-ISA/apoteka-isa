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
@Table(name="examination")
public class Examination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date startDate;
	private int duration;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PharmacistWorkCalendar pharmacistWorkCalendar;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patient;
	private Float price;
	private String report;
	
	public Examination() {
		super();
	}

	public Examination(Long id, Date startDate, int duration, PharmacistWorkCalendar pharmacistWorkCalendar,
			Patient patient, Float price, String report) {
		this.id = id;
		this.startDate = startDate;
		this.duration = duration;
		this.pharmacistWorkCalendar = pharmacistWorkCalendar;
		this.patient = patient;
		this.price = price;
		this.report = report;
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

	public PharmacistWorkCalendar getPharmacistWorkCalendar() {
		return pharmacistWorkCalendar;
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

	public void setPharmacistWorkCalendar(PharmacistWorkCalendar pharmacistWorkCalendar) {
		this.pharmacistWorkCalendar = pharmacistWorkCalendar;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
}
