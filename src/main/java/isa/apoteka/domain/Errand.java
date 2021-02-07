package isa.apoteka.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Errand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date deadline;
	private Date creationTime;
	@ManyToOne
	private Pharmacy pharmacy;
	private Boolean finished;
	
	@OneToMany(mappedBy = "errand")
	private List<MedicineQuantity> medicineForOrder;

	public Errand() {
		super();
	}

	public Errand(Long id, Date deadline, Date creationTime, Pharmacy pharmacy, Boolean finished,
			List<MedicineQuantity> medicineForOrder) {
		super();
		this.id = id;
		this.deadline = deadline;
		this.creationTime = creationTime;
		this.pharmacy = pharmacy;
		this.finished = finished;
		this.medicineForOrder = medicineForOrder;
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

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public List<MedicineQuantity> getMedicineForOrder() {
		return medicineForOrder;
	}

	public void setMedicineForOrder(List<MedicineQuantity> medicineForOrder) {
		this.medicineForOrder = medicineForOrder;
	}

	
	
	
}
