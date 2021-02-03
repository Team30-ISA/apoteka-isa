package isa.apoteka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.apoteka.dto.DermGradeDTO;
import isa.apoteka.service.DermatologistGradeService;

@RestController
@RequestMapping(value = "api/dermatologistGrade")
public class DermatologistGradeController {
	
	private DermatologistGradeService dermGradeService;
	
	@Autowired 
	public DermatologistGradeController(DermatologistGradeService dermGradeService) {
		this.dermGradeService = dermGradeService;
	}

}
