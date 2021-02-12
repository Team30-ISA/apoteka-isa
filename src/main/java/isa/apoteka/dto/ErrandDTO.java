package isa.apoteka.dto;

import isa.apoteka.domain.Errand;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;

public class ErrandDTO {
	@FutureOrPresent(message="Only date in future is accepted!")
	private Date deadline;

	public ErrandDTO(Date deadline) {
		super();
		this.deadline = deadline;
	}

	public ErrandDTO() {
		super();
	}

	public ErrandDTO(Errand e) {
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	
	
}
