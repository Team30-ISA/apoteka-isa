package isa.apoteka.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ReservedMedicine implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	String uid;
	
	@ManyToOne
	Medicine medicine;
	
    @ManyToOne
    Patient patient;

    
	private int quantity;
    
	private Date date;
	
	
	public ReservedMedicine() {
		super();
	}

	public ReservedMedicine(Medicine medicine, Patient patient, int quantity, Date date, String uid) {
		this.medicine = medicine;
		this.patient = patient;
		this.quantity = quantity;
		this.date = date;
		this.uid = uid;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public Patient getPatient() {
		return patient;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public Date getDate() {
		return date;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setUid(String s) {
		String uniqueID = UUID.randomUUID().toString();
		this.uid = uniqueID;
	}
	
	public String getUid() {
		return uid;
	}
	
	
}
