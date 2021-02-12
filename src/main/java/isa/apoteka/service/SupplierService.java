package isa.apoteka.service;

import isa.apoteka.domain.UserRequest;

public interface SupplierService {
    UserRequest update(UserRequest userRequest) throws Exception;
}
