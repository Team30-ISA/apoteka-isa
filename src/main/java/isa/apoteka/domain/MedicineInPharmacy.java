package isa.apoteka.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "MedicineInPharmacy")
@Entity
@IdClass(MedicineInPharmacyPK.class)
public class MedicineInPharmacy{

	@Id@ManyToOne
	private Medicine medicine;
	
    @Id@ManyToOne
    private Pharmacy pharmacy;

	private int quantity;

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
