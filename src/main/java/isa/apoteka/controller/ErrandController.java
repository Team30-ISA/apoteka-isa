package isa.apoteka.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import isa.apoteka.dto.ErrandDTO;
import isa.apoteka.dto.MedicineQuantityDTO;
import isa.apoteka.service.ErrandService;
import isa.apoteka.service.MedicineQuantityService;

@RestController
@RequestMapping(value = "api/errand")
public class ErrandController {

	private ErrandService errandService;
	private MedicineQuantityService medicineQuantityService;
	
	@Autowired
	public ErrandController(ErrandService errandService, MedicineQuantityService medicineQuantityService) {
		this.errandService = errandService;
		this.medicineQuantityService = medicineQuantityService;
	}
	@PostMapping("/errandMedication")
	@PreAuthorize("hasRole('ADMIN')")
	public Boolean errandMedication(@RequestBody Map<String, Object> params) throws ParseException {
		ObjectMapper obj = new ObjectMapper();
		List<MedicineQuantityDTO> dto = new ArrayList<MedicineQuantityDTO>();
		try {
			dto = obj.readValue(params.get("dto").toString(), new TypeReference<List<MedicineQuantityDTO>>() { });
		} catch (JsonProcessingException e) {
			System.out.println("greska");
		}
		System.out.println(dto.size());

		return medicineQuantityService.insert(dto);
	}
	
	
	@PostMapping("/newErrand")
	@PreAuthorize("hasRole('ADMIN')")
	public Long newErrand(@RequestBody @Valid ErrandDTO dto) throws ParseException {
		Long errandId = errandService.save(dto.getDeadline());
		return errandId;
	}
}
