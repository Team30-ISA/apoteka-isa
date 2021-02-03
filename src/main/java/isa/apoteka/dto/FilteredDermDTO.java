package isa.apoteka.dto;

import java.util.List;


public class FilteredDermDTO {
	private String firstName;
	private String lastName;
	private double grade;
	private List<String> pharmacyNames;
	public FilteredDermDTO() {
		super();
	}
	public FilteredDermDTO(String firstName, String lastName, double grade, List<String> pharmacyNames) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.pharmacyNames = pharmacyNames;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public List<String> getPharmacyNames() {
		return pharmacyNames;
	}
	public void setPharmacyNames(List<String> pharmacyNames) {
		this.pharmacyNames = pharmacyNames;
	}
	
	

}
