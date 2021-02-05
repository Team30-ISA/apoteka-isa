package isa.apoteka.dto;

import javax.persistence.Column;

import isa.apoteka.domain.DrugForm;
import isa.apoteka.domain.DrugIssuanceRegime;
import isa.apoteka.domain.DrugType;
import isa.apoteka.domain.Pharmacy;

public class MedicineDTO {
	private Long id;
	private String name;
	private DrugType type;
	private DrugForm form;
	private String contradictions;
	String composition;
	String recommendedIntakePerDay;
	String manufacturer;
	DrugIssuanceRegime regime;
	String notes;

	public MedicineDTO() {
		
	}

	/*public PharmacyDTO(Long id, String name, String address) {
		this.name = name;
		this.id = id;
		this.address = address;
	}
	
	public PharmacyDTO(Pharmacy pharmacy) {
		this(pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress());
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
