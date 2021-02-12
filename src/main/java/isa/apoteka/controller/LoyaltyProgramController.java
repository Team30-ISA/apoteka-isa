package isa.apoteka.controller;

import isa.apoteka.dto.LoyaltyProgramDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(path = "/define", method = RequestMethod.POST)
    public ResponseEntity<?> defineLoyaltyProgram(@RequestBody LoyaltyProgramDTO loyaltyProgram) {
		
		try {
			return new ResponseEntity<>(loyaltyProgramService.save(loyaltyProgram), HttpStatus.CREATED);
		} catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
