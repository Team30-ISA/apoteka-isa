package isa.apoteka.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.Promotion;

public class PromotionDTO {
	
	private String title;
	private String content;
	private Date startOfPromotion;
	private Date endOfPromotion;
	
	public PromotionDTO(String title, String content, Date startOfPromotion, Date endOfPromotion) {
		this.title = title;
		this.content = content;
		this.startOfPromotion = startOfPromotion;
		this.endOfPromotion = endOfPromotion;
	}
	public PromotionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PromotionDTO(Promotion promotion) {
		this(promotion.getTitle(), promotion.getContent(),promotion.getStartOfPromotion(), promotion.getEndOfPromotion());
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
	
	
}
