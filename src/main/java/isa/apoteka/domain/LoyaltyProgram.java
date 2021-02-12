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

	@Column(name = "Type")
	private CategoryType type;
	
	@Column(name = "CounselingPoints")
	private Integer counselingPoints;

	@Column(name = "ExaminationPoints")
	private Integer examinationPoints;

	@Column(name = "MinPoints")
	private Integer minPoints;

	@Column(name = "Discount")
	private Double discount;
	
	public LoyaltyProgram() {
		super();
	}

	public LoyaltyProgram(LoyaltyProgramDTO loyaltyProgram) {
		this.type = loyaltyProgram.getType();
		this.examinationPoints = loyaltyProgram.getExaminationPoints();
		this.minPoints = loyaltyProgram.getMinPoints();
		this.discount = loyaltyProgram.getDiscount();
		this.counselingPoints =loyaltyProgram.getCounselingPoints();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryType getType() {
		return type;
	}

	public void setType(CategoryType type) {
		this.type = type;
	}

	public Integer getMinPoints() {
		return minPoints;
	}

	public void setMinPoints(Integer minPoints) {
		this.minPoints = minPoints;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getCounselingPoints() {
		return counselingPoints;
	}

	public void setCounselingPoints(Integer counselingPoints) {
		this.counselingPoints = counselingPoints;
	}

	public Integer getExaminationPoints() {
		return examinationPoints;
	}

	public void setExaminationPoints(Integer examinationPoints) {
		this.examinationPoints = examinationPoints;
	}

}
