package isa.apoteka.service;

import isa.apoteka.domain.Authority;
import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.dto.PharmacistDTO;

import java.util.List;

public interface SysAdminService {
    public User update(UserRequest userRequest) throws Exception;

    public User create(PharmacistDTO userRequest);

    public User createSupplier(PharmacistDTO userRequest);

    public User createDermatologist(PharmacistDTO userRequest);
}
