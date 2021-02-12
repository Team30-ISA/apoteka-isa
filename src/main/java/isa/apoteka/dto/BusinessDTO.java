package isa.apoteka.dto;

import java.util.List;

public class BusinessDTO {
	private List<Double> percentageOfGrades;
	
	private List<EmployeeGradeDTO> pharmacistGrades;
	
	private List<EmployeeGradeDTO> dermatologistGrades;
	
	private Integer[] monthlyCounseling;
	
	private Integer[] quarterlyCounseling;
	
	private Integer[] yearlyCounseling;
	
	private Integer[] monthlyUsage;

	private Integer[] quarterlyUsage;
	
	private Integer[] yearlyUsage;


	public BusinessDTO() {
		super();
	}



	public BusinessDTO(List<Double> percentageOfGrades, List<EmployeeGradeDTO> pharmacistGrades,
			List<EmployeeGradeDTO> dermatologistGrades, Integer[] monthlyCounseling, Integer[] quarterlyCounseling,
			Integer[] yearlyCounseling, Integer[] monthlyUsage, Integer[] quarterlyUsage, Integer[] yearlyUsage) {
		super();
		this.percentageOfGrades = percentageOfGrades;
		this.pharmacistGrades = pharmacistGrades;
		this.dermatologistGrades = dermatologistGrades;
		this.monthlyCounseling = monthlyCounseling;
		this.quarterlyCounseling = quarterlyCounseling;
		this.yearlyCounseling = yearlyCounseling;
		this.monthlyUsage = monthlyUsage;
		this.quarterlyUsage = quarterlyUsage;
		this.yearlyUsage = yearlyUsage;
	}



	public Integer[] getMonthlyCounseling() {
		return monthlyCounseling;
	}



	public void setMonthlyCounseling(Integer[] monthlyCounseling) {
		this.monthlyCounseling = monthlyCounseling;
	}





	public Integer[] getQuarterlyCounseling() {
		return quarterlyCounseling;
	}



	public void setQuarterlyCounseling(Integer[] quarterlyCounseling) {
		this.quarterlyCounseling = quarterlyCounseling;
	}




	



	public Integer[] getYearlyCounseling() {
		return yearlyCounseling;
	}



	public void setYearlyCounseling(Integer[] yearlyCounseling) {
		this.yearlyCounseling = yearlyCounseling;
	}






	public Integer[] getMonthlyUsage() {
		return monthlyUsage;
	}



	public void setMonthlyUsage(Integer[] monthlyUsage) {
		this.monthlyUsage = monthlyUsage;
	}



	public Integer[] getQuarterlyUsage() {
		return quarterlyUsage;
	}



	public void setQuarterlyUsage(Integer[] quarterlyUsage) {
		this.quarterlyUsage = quarterlyUsage;
	}



	public Integer[] getYearlyUsage() {
		return yearlyUsage;
	}



	public void setYearlyUsage(Integer[] yearlyUsage) {
		this.yearlyUsage = yearlyUsage;
	}


	public BusinessDTO(List<Double> percentageOfGrades, List<EmployeeGradeDTO> pharmacistGrades,
			List<EmployeeGradeDTO> dermatologistGrades, Integer[] monthlyCounseling, Integer[] quarterlyCounseling,
			Integer[] yearlyCounseling, Integer[] monthlyUsage, Integer[] quarterlyUsage, Integer[] yearlyUsage,
			Double monthlySpending, Double quarterlySpending, Double yearlySpending) {
		super();
		this.percentageOfGrades = percentageOfGrades;
		this.pharmacistGrades = pharmacistGrades;
		this.dermatologistGrades = dermatologistGrades;
		this.monthlyCounseling = monthlyCounseling;
		this.quarterlyCounseling = quarterlyCounseling;
		this.yearlyCounseling = yearlyCounseling;
		this.monthlyUsage = monthlyUsage;
		this.quarterlyUsage = quarterlyUsage;
		this.yearlyUsage = yearlyUsage;
	}



	public List<Double> getPercentageOfGrades() {
		return percentageOfGrades;
	}

	public void setPercentageOfGrades(List<Double> percentageOfGrades) {
		this.percentageOfGrades = percentageOfGrades;
	}

	public List<EmployeeGradeDTO> getPharmacistGrades() {
		return pharmacistGrades;
	}

	public void setPharmacistGrades(List<EmployeeGradeDTO> pharmacistGrades) {
		this.pharmacistGrades = pharmacistGrades;
	}

	public List<EmployeeGradeDTO> getDermatologistGrades() {
		return dermatologistGrades;
	}

	public void setDermatologistGrades(List<EmployeeGradeDTO> dermatologistGrades) {
		this.dermatologistGrades = dermatologistGrades;
	}



	
	
	
}
