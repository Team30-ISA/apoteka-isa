package isa.apoteka.controller;

import isa.apoteka.dto.LoyaltyProgramDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public ResponseEntity<?> getLoyalty() {
		try {
            return new ResponseEntity<>(loyaltyProgramService.getLoyaltyProgram(), HttpStatus.OK);
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
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody LoyaltyProgramDTO loyaltyProgramDTO) {
		try {
			return new ResponseEntity<>(loyaltyProgramService.update(loyaltyProgramDTO), HttpStatus.OK);
	   } catch (Exception e) {
	       	return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	   }
	}
}
