package isa.apoteka.dto;

import javax.persistence.Column;

import isa.apoteka.domain.*;

import java.util.Date;


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
	private int quantity;
	private int price;
	private Date startOfPrice;
	private Date endOfPrice;

	
	public MedicineDTO() {
		super();
	}
	public MedicineDTO(Long id, String name, int quantity, int price, Date startOfPrice, Date endOfPrice) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.startOfPrice = startOfPrice;
		this.endOfPrice = endOfPrice;
	}

	public MedicineDTO(Medicine medicine, int quantity, int price) {
		this.id = medicine.getId();
		this.name = medicine.getName();
		this.quantity = quantity;
		this.price = price;
	}
	
	public MedicineDTO(Medicine medicine, int quantity, int price, DrugType type, DrugForm form) {
		this.id = medicine.getId();
		this.name = medicine.getName();
		this.quantity = quantity;
		this.price = price;
		this.type = type;
		this.form = form;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getStartOfPrice() {
		return startOfPrice;
	}
	public void setStartOfPrice(Date startOfPrice) {
		this.startOfPrice = startOfPrice;
	}
	public Date getEndOfPrice() {
		return endOfPrice;
	}
	public void setEndOfPrice(Date endOfPrice) {
		this.endOfPrice = endOfPrice;
	}

	public DrugType getType() {
		return type;
	}

	public void setType(DrugType type) {
		this.type = type;
	}

	public DrugForm getForm() {
		return form;
	}

	public void setForm(DrugForm form) {
		this.form = form;
	}

	public String getContradictions() {
		return contradictions;
	}

	public void setContradictions(String contradictions) {
		this.contradictions = contradictions;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getRecommendedIntakePerDay() {
		return recommendedIntakePerDay;
	}

	public void setRecommendedIntakePerDay(String recommendedIntakePerDay) {
		this.recommendedIntakePerDay = recommendedIntakePerDay;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public DrugIssuanceRegime getRegime() {
		return regime;
	}

	public void setRegime(DrugIssuanceRegime regime) {
		this.regime = regime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
