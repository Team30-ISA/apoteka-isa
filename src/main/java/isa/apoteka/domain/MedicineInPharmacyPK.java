package isa.apoteka.domain;

import java.io.Serializable;

public class MedicineInPharmacyPK implements Serializable{
	private static final long serialVersionUID = 1L;

	private Medicine medicine;
	
    private Pharmacy pharmacy;

	public MedicineInPharmacyPK() {
	}

	public MedicineInPharmacyPK(Medicine medicine, Pharmacy pharmacy) {
		super();
		this.medicine = medicine;
		this.pharmacy = pharmacy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicine == null) ? 0 : medicine.hashCode());
		result = prime * result + ((pharmacy == null) ? 0 : pharmacy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicineInPharmacyPK other = (MedicineInPharmacyPK) obj;
		if (medicine == null) {
			if (other.medicine != null)
				return false;
		} else if (!medicine.equals(other.medicine))
			return false;
		if (pharmacy == null) {
			if (other.pharmacy != null)
				return false;
		} else if (!pharmacy.equals(other.pharmacy))
			return false;
		return true;
	}
}
