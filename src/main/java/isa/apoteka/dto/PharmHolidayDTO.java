package isa.apoteka.dto;

import java.util.Date;

import isa.apoteka.domain.LeaveRequestStatus;

public class PharmHolidayDTO {
	private Long holidayId;
	private Long pharmId;
	private String firstName;
	private String lastName;
	private Date start;
	private Date end;
	private LeaveRequestStatus status;
	public PharmHolidayDTO() {
		super();
	}
	public PharmHolidayDTO(Long holidayId, Long pharmId, String firstName, String lastName, Date start, Date end,
			LeaveRequestStatus status) {
		super();
		this.holidayId = holidayId;
		this.pharmId = pharmId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.start = start;
		this.end = end;
		this.status = status;
	}
	public Long getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(Long holidayId) {
		this.holidayId = holidayId;
	}
	public Long getPharmId() {
		return pharmId;
	}
	public void setPharmId(Long pharmId) {
		this.pharmId = pharmId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public LeaveRequestStatus getStatus() {
		return status;
	}
	public void setStatus(LeaveRequestStatus status) {
		this.status = status;
	}

	
	
}
