package isa.apoteka.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="therapy")
public class Therapy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int duration;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Patient patient;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medicine medicine;
	
	public Therapy() {
		super();
	}
	
	public Therapy(int duration, Patient patient, Medicine medicine) {
		this.duration = duration;
		this.patient = patient;
		this.medicine = medicine;
	}

	public Therapy(Long id, int duration, Patient patient, Medicine medicine) {
		this.id = id;
		this.duration = duration;
		this.patient = patient;
		this.medicine = medicine;
	}

	public Long getId() {
		return id;
	}

	public int getDuration() {
		return duration;
	}

	public Patient getPatient() {
		return patient;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}	

}
