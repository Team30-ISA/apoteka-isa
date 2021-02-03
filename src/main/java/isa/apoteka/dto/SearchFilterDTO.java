package isa.apoteka.dto;

import java.util.List;

import javax.validation.constraints.Size;

import isa.apoteka.domain.Pharmacy;

public class SearchFilterDTO {
	private String firstName;
	private String lastName;
	private int minGrade;
	private int maxGrade;
	private List<Long> pharmacies;
	public SearchFilterDTO() {
		super();
	}
	public SearchFilterDTO(String firstName, String lastName, int minGrade, int maxGrade,
			List<Long> pharmacies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.minGrade = minGrade;
		this.maxGrade = maxGrade;
		this.pharmacies = pharmacies;
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
	public List<Long> getPharmacies() {
		return pharmacies;
	}
	public void setPharmacies(List<Long> pharmacies) {
		this.pharmacies = pharmacies;
	} 
	
	
	
}
