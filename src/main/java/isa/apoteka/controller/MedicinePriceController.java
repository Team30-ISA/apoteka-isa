package isa.apoteka.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.dto.ChangePriceDTO;
import isa.apoteka.service.MedicinePriceService;

@RestController
@RequestMapping(value = "api/medicinePrice")
public class MedicinePriceController {

	private MedicinePriceService medPriceService;
	
	@Autowired
	public MedicinePriceController(MedicinePriceService medPriceService) {
		this.medPriceService = medPriceService;
	}

	@PostMapping(value= "/changePrice", consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ChangePriceDTO> addMedicine(@RequestBody @Valid ChangePriceDTO changePriceDTO) {
		medPriceService.changePrice(changePriceDTO);
		return new ResponseEntity<>(changePriceDTO, HttpStatus.CREATED);
		
	}
}
