package isa.apoteka.dto;

import isa.apoteka.domain.DrugType;

public class SearchFilterMedicineDTO {
	private String name;
	private int minGrade;
	private int maxGrade;
	private DrugType drugType;
	
	public SearchFilterMedicineDTO() {
		super();
	}
	
	public SearchFilterMedicineDTO(String name, int minGrade, int maxGrade, DrugType drugType) {
		super();
		this.name = name;
		this.minGrade = minGrade;
		this.maxGrade = maxGrade;
		this.drugType = drugType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinGrade() {
		return minGrade;
	}

	public void setMinGrade(int minGrade) {
		this.minGrade = minGrade;
	}

	public int getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(int maxGrade) {
		this.maxGrade = maxGrade;
	}

	public DrugType getDrugType() {
		return drugType;
	}

	public void setDrugType(DrugType drugType) {
		this.drugType = drugType;
	}
	
}
