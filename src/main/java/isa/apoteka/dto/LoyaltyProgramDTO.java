package isa.apoteka.dto;



import isa.apoteka.domain.CategoryType;

public class LoyaltyProgramDTO {
	private Long id;
	private CategoryType type;
	private Integer examinationPoints;
	private Integer minPoints;
	private Double discount;
	private Integer counselingPoints;

	public LoyaltyProgramDTO() {
	}

	public LoyaltyProgramDTO(Long id, CategoryType type, Integer examinationPoints, Integer minPoints,
							 Double discount) {
		super();
		this.id = id;
		this.type = type;
		this.examinationPoints = examinationPoints;
		this.minPoints = minPoints;
		this.discount = discount;
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
	public Integer getExaminationPoints() {
		return examinationPoints;
	}
	public void setExaminationPoints(Integer examinationPoints) {
		this.examinationPoints = examinationPoints;
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
}
