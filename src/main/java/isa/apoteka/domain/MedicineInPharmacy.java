package isa.apoteka.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MedicineInPharmacy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id@ManyToOne
	Medicine medicine;
	
    @Id@ManyToOne
    Pharmacy pharmacy;

	private int quantity;
	
	
	public MedicineInPharmacy() {
		super();
	}

	public MedicineInPharmacy(Medicine medicine, Pharmacy pharmacy, int quantity) {
		this.medicine = medicine;
		this.pharmacy = pharmacy;
		this.quantity = quantity;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
