package isa.apoteka.domain;

import isa.apoteka.dto.LoyaltyProgramDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LoyaltyProgram") 
public class LoyaltyProgram {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "counselingPoints")
	private Double counselingPoints;

	@Column(name = "examinationPoints")
	private Double examinationPoints;

	@Column(name = "regularMinPoints")
	private Integer regularMinPoints;

	@Column(name = "regularDiscount")
	private Double regularDiscount;

	@Column(name = "silverMinPoints")
	private Integer silverMinPoints;
	
	@Column(name = "silverDiscount")
    private Double silverDiscount;

	@Column(name = "goldenMinPoints")
	private Integer goldenMinPoints;
	
    @Column(name = "goldenDiscount")
    private Double goldenDiscount;
	
	public LoyaltyProgram() {
		super();
	}
	
	public LoyaltyProgram(LoyaltyProgramDTO loyaltyProgramDTO) {
		this.counselingPoints = loyaltyProgramDTO.getCounselingPoints();
		this.examinationPoints = loyaltyProgramDTO.getExaminationPoints();
		this.regularMinPoints = loyaltyProgramDTO.getRegularMinPoints();
		this.regularDiscount = loyaltyProgramDTO.getRegularDiscount();
		this.silverMinPoints = loyaltyProgramDTO.getSilverMinPoints();
		this.silverDiscount = loyaltyProgramDTO.getSilverDiscount();
		this.goldenMinPoints = loyaltyProgramDTO.getGoldenMinPoints();
		this.goldenDiscount = loyaltyProgramDTO.getGoldenDiscount();
	}

	/*public LoyaltyProgram(Long id, Double counselingPoints, Double examinationPoints, Integer regularMinPoints,
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
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setCounselingPoints(Double counselingPoints) {
		this.counselingPoints = counselingPoints;
	}

	public void setExaminationPoints(Double examinationPoints) {
		this.examinationPoints = examinationPoints;
	}

	public Double getCounselingPoints() {
		return counselingPoints;
	}

	public Double getExaminationPoints() {
		return examinationPoints;
	}

}
