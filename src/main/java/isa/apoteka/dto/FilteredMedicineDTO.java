package isa.apoteka.dto;

import java.util.List;

public class FilteredMedicineDTO {
	private String medicineName;
	private double grade;
	private List<String> drugTypeNames;
	
	public FilteredMedicineDTO() {
		super();
	}
	
	public FilteredMedicineDTO(String medicineName, double grade, List<String> drugTypeNames) {
		super();
		this.medicineName = medicineName;
		this.grade = grade;
		this.drugTypeNames = drugTypeNames;
	}
	
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public List<String> getDrugTypeNames() {
		return drugTypeNames;
	}
	public void setDrugTypeNames(List<String> drugTypeNames) {
		this.drugTypeNames = drugTypeNames;
	}
	
	
}
