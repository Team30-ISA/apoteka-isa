package isa.apoteka.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = false, nullable = false)
	String title;
	
	@Column(unique = false, nullable = false)
	String content;
	
	@Column(unique = false, nullable = false)
	Date startOfPromotion;
	
	@Column(unique = false, nullable = false)
	Date endOfPromotion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Promotion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Promotion(Long id, String title, String content, Date startOfPromotion, Date endOfPromotion) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.startOfPromotion = startOfPromotion;
		this.endOfPromotion = endOfPromotion;
	}
	
	 
}
