package isa.apoteka.dto;

import isa.apoteka.domain.LoyaltyProgram;

public class LoyaltyProgramDTO {
	private Long id;
	private Double counselingPoints;
	private Double examinationPoints;
	private Integer regularMinPoints;
	private Double regularDiscount;
	private Integer silverMinPoints;
    private Double silverDiscount;
	private Integer goldenMinPoints;
    private Double goldenDiscount;

	public LoyaltyProgramDTO() {
	}

	public LoyaltyProgramDTO(LoyaltyProgram loyaltyProgram) {
		this.id = loyaltyProgram.getId();
		this.counselingPoints = loyaltyProgram.getCounselingPoints();
		this.examinationPoints = loyaltyProgram.getExaminationPoints();
		this.regularMinPoints = loyaltyProgram.getRegularMinPoints();
		this.regularDiscount = loyaltyProgram.getRegularDiscount();
		this.silverMinPoints = loyaltyProgram.getSilverMinPoints();
		this.silverDiscount = loyaltyProgram.getSilverDiscount();
		this.goldenMinPoints = loyaltyProgram.getGoldenMinPoints();
		this.goldenDiscount = loyaltyProgram.getGoldenDiscount();
	}
	
	public LoyaltyProgramDTO(Long id, Double counselingPoints, Double examinationPoints, Integer regularMinPoints,
			Double regularDiscount, Integer silverMinPoints, Double silverDiscount, Integer goldenMinPoints,
			Double goldenDiscount) {
		super();
		this.id = id;
		this.counselingPoints = counselingPoints;
		this.examinationPoints = examinationPoints;
		this.regularMinPoints = regularMinPoints;
		this.regularDiscount = regularDiscount;
		this.silverMinPoints = silverMinPoints;
		this.silverDiscount = silverDiscount;
		this.goldenMinPoints = goldenMinPoints;
		this.goldenDiscount = goldenDiscount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCounselingPoints() {
		return counselingPoints;
	}

	public void setCounselingPoints(Double counselingPoints) {
		this.counselingPoints = counselingPoints;
	}

	public Double getExaminationPoints() {
		return examinationPoints;
	}

	public void setExaminationPoints(Double examinationPoints) {
		this.examinationPoints = examinationPoints;
	}

	public Integer getRegularMinPoints() {
		return regularMinPoints;
	}

	public void setRegularMinPoints(Integer regularMinPoints) {
		this.regularMinPoints = regularMinPoints;
	}

	public Double getRegularDiscount() {
		return regularDiscount;
	}

	public void setRegularDiscount(Double regularDiscount) {
		this.regularDiscount = regularDiscount;
	}

	public Integer getSilverMinPoints() {
		return silverMinPoints;
	}

	public void setSilverMinPoints(Integer silverMinPoints) {
		this.silverMinPoints = silverMinPoints;
	}

	public Double getSilverDiscount() {
		return silverDiscount;
	}

	public void setSilverDiscount(Double silverDiscount) {
		this.silverDiscount = silverDiscount;
	}

	public Integer getGoldenMinPoints() {
		return goldenMinPoints;
	}

	public void setGoldenMinPoints(Integer goldenMinPoints) {
		this.goldenMinPoints = goldenMinPoints;
	}

	public Double getGoldenDiscount() {
		return goldenDiscount;
	}

	public void setGoldenDiscount(Double goldenDiscount) {
		this.goldenDiscount = goldenDiscount;
	}
}
