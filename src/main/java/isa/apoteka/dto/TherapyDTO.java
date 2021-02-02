package isa.apoteka.dto;

import isa.apoteka.domain.Therapy;

public class TherapyDTO {
	private Long id;
	private int duration;
	private String patientName;
	private String medicineName;
	private String medicineManuf;
	
	
	public TherapyDTO() {
		super();
	}
		
	public TherapyDTO(Long id, int duration, String patientName, String medicineName, String medicineManuf) {
		this.id = id;
		this.duration = duration;
		this.patientName = patientName;
		this.medicineName = medicineName;
		this.medicineManuf = medicineManuf;
	}

	public TherapyDTO(Therapy therapy) {
		this(therapy.getId(), therapy.getDuration(), therapy.getPatient().getFirstName() + " " + therapy.getPatient().getLastName(), therapy.getMedicine().getName(), therapy.getMedicine().getManufacturer());
	}
	
	public Long getId() {
		return id;
	}
	public int getDuration() {
		return duration;
	}
	public String getPatientName() {
		return patientName;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public String getMedicineManuf() {
		return medicineManuf;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public void setMedicineManuf(String medicineManuf) {
		this.medicineManuf = medicineManuf;
	}
	
}
