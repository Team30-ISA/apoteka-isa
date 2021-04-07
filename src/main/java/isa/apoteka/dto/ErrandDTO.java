package isa.apoteka.dto;

import isa.apoteka.domain.Errand;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;

import org.hibernate.annotations.Type;

public class ErrandDTO {
	@FutureOrPresent(message="Only date in future is accepted!")
	
	private Date deadline;
	@Type(type = "pg-Long")
	private Long id;

	public ErrandDTO(Date deadline, Long id) {
		super();
		this.deadline = deadline;
		this.id = id;
	}

	public ErrandDTO() {
		super();
	}

	public ErrandDTO(Errand e) {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	
	
}
