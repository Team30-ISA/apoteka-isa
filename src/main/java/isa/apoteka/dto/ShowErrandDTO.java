package isa.apoteka.dto;

import java.util.Date;
import java.util.List;

public class ShowErrandDTO {
	private Long id;
	private Date start;
	private Date deadline;
	List<MedicineForSupplyDTO> medicines;
	List<SupplierDTO> suppliers;
	public ShowErrandDTO() {
		super();
	}
	public ShowErrandDTO(Long id, Date start, Date deadline, List<MedicineForSupplyDTO> medicines,
			List<SupplierDTO> suppliers) {
		super();
		this.id = id;
		this.start = start;
		this.deadline = deadline;
		this.medicines = medicines;
		this.suppliers = suppliers;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public List<MedicineForSupplyDTO> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<MedicineForSupplyDTO> medicines) {
		this.medicines = medicines;
	}
	public List<SupplierDTO> getSuppliers() {
		return suppliers;
	}
	public void setSuppliers(List<SupplierDTO> suppliers) {
		this.suppliers = suppliers;
	}
	
	
	
}
