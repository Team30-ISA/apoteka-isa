package isa.apoteka.dto;

public class EmployeeGradeDTO {

	private String firstName;
	private String lastName;
	private double grade;
	public EmployeeGradeDTO(String firstName, String lastName, double grade) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
	}
	public EmployeeGradeDTO() {
		super();
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
	
	
	
	
}
