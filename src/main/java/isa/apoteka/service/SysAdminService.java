package isa.apoteka.service;

import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;

public interface SysAdminService {
    public User update(UserRequest userRequest) throws Exception;
}
