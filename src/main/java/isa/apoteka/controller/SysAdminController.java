package isa.apoteka.controller;

import isa.apoteka.domain.UserRequest;
import isa.apoteka.dto.ComplaintResponseDTO;
import isa.apoteka.dto.PharmacistDTO;
import isa.apoteka.service.ComplaintService;
import isa.apoteka.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/sys-admin")
public class SysAdminController {

    private SysAdminService sysAdminService;
    private ComplaintService complaintService;
    
    @Autowired
    public SysAdminController(SysAdminService sysAdminService, ComplaintService complaintService) {
		this.sysAdminService = sysAdminService;
		this.complaintService = complaintService;
	}

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

    @GetMapping(value= "complaint-data")
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> getAllComplaints() {
        try {
            return new ResponseEntity<>(complaintService.getAllComplaints(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="answer")
    @PreAuthorize("hasRole('SYS_ADMIN')")
    public ResponseEntity<?> respondToComplaint(@RequestBody ComplaintResponseDTO complaintResponseDTO) {
        try {
            return new ResponseEntity<>(complaintService.respond(complaintResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
