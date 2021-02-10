package isa.apoteka.dto;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.Promotion;

public class PromotionDTO {
	@Size(min=2, max=50, message="Title has to have at least 2 charachters and max 50.")
	private String title;
	@Size(min=2, max=255, message="Content has to have at least 2 charachters and max 255.")
	private String content;
	@FutureOrPresent(message="Start of promotion has to be a future or present date.")
	private Date startOfPromotion;
	@FutureOrPresent(message="End of promotion has to be a future date.")
	private Date endOfPromotion;
	@NotNull
	private Long pharmacyId;
	
	public PromotionDTO(String title, String content, Date startOfPromotion, Date endOfPromotion, Pharmacy pharmacy, Long pharmacyId) {
		this.title = title;
		this.content = content;
		this.startOfPromotion = startOfPromotion;
		this.endOfPromotion = endOfPromotion;
		this.pharmacyId = pharmacyId;
	}
	
	public PromotionDTO(String title, String content, Date startOfPromotion, Date endOfPromotion, Pharmacy pharmacy) {
		this.title = title;
		this.content = content;
		this.startOfPromotion = startOfPromotion;
		this.endOfPromotion = endOfPromotion;
	}
	
	public PromotionDTO() {
		super();
	}
	
	public PromotionDTO(Promotion promotion) {
		this(promotion.getTitle(), promotion.getContent(),promotion.getStartOfPromotion(), promotion.getEndOfPromotion(), promotion.getPharmacy());
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartOfPromotion() {
		return startOfPromotion;
	}
	public void setStartOfPromotion(Date startOfPromotion) {
		this.startOfPromotion = startOfPromotion;
	}
	public Date getEndOfPromotion() {
		return endOfPromotion;
	}
	public void setEndOfPromotion(Date endOfPromotion) {
		this.endOfPromotion = endOfPromotion;
	}

	public Long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	
	
	
}
