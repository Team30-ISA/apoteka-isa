package isa.apoteka.controller;

import isa.apoteka.domain.Supplier;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/getLoggedUser")
	@PreAuthorize("hasRole('SUPL')")
	public Supplier getLoggedUser() {
		return (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
    
    @PutMapping
    @PreAuthorize("hasRole('SUPL')")
    public ResponseEntity<?> update(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(supplierService.update(userRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
