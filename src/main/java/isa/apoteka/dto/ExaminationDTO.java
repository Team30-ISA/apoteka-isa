package isa.apoteka.dto;

import java.util.Date;

public class ExaminationDTO {
	private Long id;
	private Date startDate;
	private int duration;
	private String pharmacyName;
	private String patientName;
	private float price;
	private String report;
	
	public ExaminationDTO() {
		super();
	}

	public ExaminationDTO(Long id, Date startDate, int duration, String pharmacyName, String patientName, float price, String report) {
		this.id = id;
		this.startDate = startDate;
		this.duration = duration;
		this.pharmacyName = pharmacyName;
		this.patientName = patientName;
		this.price = price;
		this.report = report;
	}

	public Long getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public int getDuration() {
		return duration;
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public String getPatientName() {
		return patientName;
	}

	public float getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}
	
}
