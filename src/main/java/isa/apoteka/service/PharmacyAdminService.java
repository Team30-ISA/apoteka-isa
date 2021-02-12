package isa.apoteka.service;

import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.PharmacistDTO;

public interface PharmacyAdminService {
	public void update(String firstName, String lastName, Long id);

    public PharmacyAdmin create(PharmacistDTO pharmacyAdmin);
}
