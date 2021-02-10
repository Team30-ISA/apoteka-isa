package isa.apoteka.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PharmacyGrade {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique=true, nullable=false)
    private Long id;
	
	private int grade;

	@ManyToOne
	private Pharmacy pharmacy;

	@ManyToOne
	private Patient patient;

	public PharmacyGrade() {
		super();
	}

	public PharmacyGrade(Long id, int grade, Pharmacy pharmacy, Patient patient) {
		super();
		this.id = id;
		this.grade = grade;
		this.pharmacy = pharmacy;
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

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
}
