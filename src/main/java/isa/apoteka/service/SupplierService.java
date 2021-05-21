package isa.apoteka.service;

import isa.apoteka.domain.Supplier;
import isa.apoteka.domain.UserRequest;

public interface SupplierService {
    UserRequest update(UserRequest userRequest) throws Exception;
    Supplier findById(Long id);
}
