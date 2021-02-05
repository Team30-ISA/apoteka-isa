package isa.apoteka.dto;

import isa.apoteka.domain.Address;
import isa.apoteka.domain.Gender;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.User;

public class PharmacistDTO extends UserDTO{
	private Long pharmacyId;

	public PharmacistDTO() {
		super();
	}

	public PharmacistDTO(Long id, Gender gender, String username, String firstName, String lastName, String email, Address address, String phonenumber, Long pharmacyId) {
		super(id, gender, username, firstName, lastName, email, address, phonenumber);
		this.pharmacyId = pharmacyId;
	}

	public PharmacistDTO(User user) {
		super(user);
	}
	
	public PharmacistDTO(Pharmacist pharmacist) {
		super(pharmacist);
		if(pharmacist.getPharmacy() != null)
			this.pharmacyId = pharmacist.getPharmacy().getId();
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	
	

}
