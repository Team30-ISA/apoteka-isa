package isa.apoteka.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MedicineQuantity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Medicine medicine;
	
	private int quantity;
	
	@ManyToOne
	private Errand errand;
	
	
	public MedicineQuantity() {
		super();
	}


	public MedicineQuantity(Long id, Medicine medicine, int quantity, Errand errand) {
		super();
		this.id = id;
		this.medicine = medicine;
		this.quantity = quantity;
		this.errand = errand;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Medicine getMedicine() {
		return medicine;
	}


	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Errand getErrand() {
		return errand;
	}


	public void setErrand(Errand errand) {
		this.errand = errand;
	}
	

	
	
	
	
}
