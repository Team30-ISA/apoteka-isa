package isa.apoteka.dto;

public class DermGradeDTO {
	private Long dermId;
	private Double grade;
	public DermGradeDTO() {
		super();
	}
	public DermGradeDTO(Long dermId, Double grade) {
		super();
		this.dermId = dermId;
		this.grade = grade;
	}
	public Long getDermId() {
		return dermId;
	}
	public void setDermId(Long dermId) {
		this.dermId = dermId;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
}
