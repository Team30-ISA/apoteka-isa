package isa.apoteka.dto;

import java.util.Date;

public class PeriodDTO {
	private Date startDate;
	private Date endDate;
	
	public PeriodDTO() {
		super();
	}

	public PeriodDTO(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
