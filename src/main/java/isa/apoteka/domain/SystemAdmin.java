package isa.apoteka.domain;

import isa.apoteka.dto.PharmacistDTO;

import javax.persistence.Entity;

@Entity
public class SystemAdmin extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6152435523481630177L;

	public SystemAdmin() {
		super();
	}

    public SystemAdmin(PharmacistDTO sysAdminData) {
    	super(sysAdminData);
    }

    public SystemAdmin(UserRequest userRequest) {
    	super(userRequest);
    }
}
