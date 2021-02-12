package isa.apoteka.controller;

import isa.apoteka.domain.Authority;
import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.service.AuthorityService;
import isa.apoteka.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/sys-admin")
public class SysAdminController {

    @Autowired
    private SysAdminService sysAdminService;

    @PutMapping
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> update(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(sysAdminService.update(userRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> createSysAdmin(@RequestBody PharmacistDTO userRequest) {
        try {
            return new ResponseEntity<>(sysAdminService.create(userRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="derm")
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> createDermatologist(@RequestBody PharmacistDTO userRequest) {
        try {
            return new ResponseEntity<>(sysAdminService.createDermatologist(userRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value="suppl")
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> createSupplier(@RequestBody PharmacistDTO userRequest) {
        try {
            return new ResponseEntity<>(sysAdminService.createSupplier(userRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
