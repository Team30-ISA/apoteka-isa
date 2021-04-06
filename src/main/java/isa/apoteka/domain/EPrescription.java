package isa.apoteka.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="EPrescriptions")
public class EPrescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

    @Column(name = "Code", unique = true)
    private String code;

    @JsonBackReference(value="EPrescriptionPatient")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "id", unique = false)
    private Patient patient;
    
    @JsonManagedReference(value="EPrescriptionMedicine")
    @OneToMany(mappedBy = "ePrescription", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<MedicineEPrescription> medications = new HashSet<MedicineEPrescription>();
    
    @Column(name = "pharmacyId", nullable = false)
    private Long pharmacyId;

    @Column(name = "Date")
    private Date date;

    public EPrescription() {}
    
	public EPrescription(Long id, String code, Patient patient, Set<MedicineEPrescription> medications, Long pharmacyId,
			Date date) {
		this.id = id;
		this.code = code;
		this.patient = patient;
		this.medications = medications;
		this.pharmacyId = pharmacyId;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Set<MedicineEPrescription> getMedications() {
		return medications;
	}

	public void setMedications(Set<MedicineEPrescription> medications) {
		this.medications = medications;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
    
	
}
