package isa.apoteka.domain;

import isa.apoteka.dto.PharmacistDTO;

import javax.persistence.Entity;

@Entity
public class Supplier extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3619233426695840684L;

	public Supplier() {
		super();
	}

	public Supplier(PharmacistDTO dermatologistData) {
    	super(dermatologistData);
    }
}
