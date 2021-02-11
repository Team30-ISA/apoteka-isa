package isa.apoteka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.domain.LoyaltyProgram;
import isa.apoteka.service.LoyaltyProgramService;

@RestController
@RequestMapping(value = "api/loyalty")
public class LoyaltyProgramController {

	@Autowired
	private LoyaltyProgramService loyaltyProgramService;
	
	@GetMapping("/getAllLoyalties")
	public ResponseEntity<?> getAll() {
		try {
            return new ResponseEntity<>(loyaltyProgramService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}
	
	@PostMapping("/define")
    public ResponseEntity<?> defineLoyaltyProgram(@RequestBody LoyaltyProgram loyaltyProgram) {
		
		try {
			return new ResponseEntity<>(loyaltyProgramService.save(loyaltyProgram), HttpStatus.CREATED);
		} catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
