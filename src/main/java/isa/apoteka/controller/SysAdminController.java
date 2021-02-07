package isa.apoteka.controller;

import isa.apoteka.domain.User;
import isa.apoteka.domain.UserRequest;
import isa.apoteka.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
