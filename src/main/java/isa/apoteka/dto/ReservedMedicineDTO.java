package isa.apoteka.dto;

import java.util.Date;

import isa.apoteka.domain.ReservedMedicine;

public class ReservedMedicineDTO {
	private String uid;
	private Long pharmacyId;
	private String pharmacyName;
	private Long patientId;
	private String patientName;
	private Long medicineId;
	private String medicineName;
	private Date date;
	
	public ReservedMedicineDTO() {
		super();
	}
	
	public ReservedMedicineDTO(ReservedMedicine rm) {
		this.uid = rm.getUid();
		this.pharmacyId = rm.getPharmacy().getId();
		this.pharmacyName = rm.getPharmacy().getName();
		this.patientId = rm.getPatient().getId();
		this.patientName = rm.getPatient().getFirstName() + " " + rm.getPatient().getLastName();
		this.medicineId = rm.getMedicine().getId();
		this.medicineName = rm.getMedicine().getName();
		this.date = rm.getDate();
	}
	
	public String getUid() {
		return uid;
	}
	public Long getPharmacyId() {
		return pharmacyId;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public Long getPatientId() {
		return patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public Long getMedicineId() {
		return medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public Date getDate() {
		return date;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setPharmacyId(Long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
