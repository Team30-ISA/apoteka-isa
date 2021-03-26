package isa.apoteka.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import isa.apoteka.domain.Errand;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.ErrandDTO;
import isa.apoteka.dto.MedicineForSupplyDTO;
import isa.apoteka.dto.MedicineQuantityDTO;
import isa.apoteka.dto.ShowErrandDTO;
import isa.apoteka.dto.SupplierDTO;
import isa.apoteka.service.ErrandService;
import isa.apoteka.service.MedicineQuantityService;
import isa.apoteka.service.OfferService;

@RestController
@RequestMapping(value = "api/errand")
public class ErrandController {

	private ErrandService errandService;
	private MedicineQuantityService medicineQuantityService;
	private OfferService offerService;
	
	@Autowired
	public ErrandController(ErrandService errandService, MedicineQuantityService medicineQuantityService, OfferService offerService) {
		this.errandService = errandService;
		this.medicineQuantityService = medicineQuantityService;
		this.offerService = offerService;
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
		System.out.println(dto);

		return medicineQuantityService.insert(dto);
	}
	
	
	@PostMapping("/newErrand")
	@PreAuthorize("hasRole('ADMIN')")
	public Long newErrand(@RequestBody @Valid ErrandDTO dto) throws ParseException {
		try {
			return errandService.save(dto.getDeadline());
        } catch (Exception e) {
            return (long)1;
        }
	}
	
	@GetMapping("/findAllErrands")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<ShowErrandDTO>> findallErrands(Boolean approved) {
		List<ShowErrandDTO> errands = new ArrayList<ShowErrandDTO>();
		List<Errand> list = errandService.findAllErrands();
		for(Errand e : list) {
			if(e.getFinished().equals(approved)) {
				ShowErrandDTO dto = new ShowErrandDTO();
				List<MedicineForSupplyDTO> medicines = medicineQuantityService.getMedicineForErrand(e.getId());
				List<SupplierDTO> suppliers = offerService.findAllOffersForErrand(e.getId());
				dto.setMedicines(medicines);
				dto.setSuppliers(suppliers);
				dto.setId(e.getId());
				dto.setDeadline(e.getDeadline());
				dto.setStart(e.getCreationTime());
				dto.setFinished(e.getFinished());
				
				errands.add(dto);
			}
		}
		return new ResponseEntity<>(errands, HttpStatus.OK);
	}
	
	@GetMapping("/findAllErrand")
	public ResponseEntity<List<ShowErrandDTO>> findAllErrands(Boolean approved) {
		List<ShowErrandDTO> errands = new ArrayList<ShowErrandDTO>();
		List<Errand> list = errandService.findAllErrands();
		for(Errand e : list) {
			if(e.getFinished().equals(approved)) {
				ShowErrandDTO dto = new ShowErrandDTO();
				List<MedicineForSupplyDTO> medicines = medicineQuantityService.getMedicineForErrand(e.getId());
				List<SupplierDTO> suppliers = offerService.findAllOffersForErrand(e.getId());
				dto.setMedicines(medicines);
				dto.setSuppliers(suppliers);
				dto.setId(e.getId());
				dto.setDeadline(e.getDeadline());
				dto.setStart(e.getCreationTime());
				dto.setFinished(e.getFinished());
				
				errands.add(dto);
			}
		}
		return new ResponseEntity<>(errands, HttpStatus.OK);
	}
	
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long errandId) {
        PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        errandService.delete(errandId, admin);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}
