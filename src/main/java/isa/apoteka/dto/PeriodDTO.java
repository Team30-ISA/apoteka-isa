package isa.apoteka.dto;

import java.util.Date;

public class PeriodDTO {
	private Long id;
	private Date startDate;
	private Date endDate;
	
	public PeriodDTO() {
		super();
	}

	public PeriodDTO(Date startDate, Date endDate, Long id) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.id = id;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
