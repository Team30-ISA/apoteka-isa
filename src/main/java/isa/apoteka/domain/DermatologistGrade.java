package isa.apoteka.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DermatologistGrade")
public class DermatologistGrade {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique=true, nullable=false)
    private Long id;

	private int grade;

	@ManyToOne
	private Dermatologist dermatologist;

	@ManyToOne
	private Patient patient;

	public DermatologistGrade() {
		super();
	}

	public DermatologistGrade(Long id, int grade, Dermatologist dermatologist, Patient patient) {
		super();
		this.id = id;
		this.grade = grade;
		this.dermatologist = dermatologist;
		this.patient = patient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Dermatologist getDermatologists() {
		return dermatologist;
	}

	public void setDermatologists(Dermatologist dermatologists) {
		this.dermatologist = dermatologist;
	}

	public Patient getPatients() {
		return patient;
	}

	public void setPatients(Patient patient) {
		this.patient = patient;
	}
	
}
