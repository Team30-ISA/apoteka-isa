package isa.apoteka.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Future;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Errand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = false, nullable = false)
	@Future
	private Date deadline;
	
	@Column(unique = false, nullable = false)
	private Date creationTime;
	
	@ManyToOne
	@NotNull
	private Pharmacy pharmacy;
	
	@ManyToOne
	private PharmacyAdmin admin;
	
	@Column(unique = false, nullable = false)
	private Boolean finished;
	
	@OneToMany(mappedBy = "errand")
	private List<MedicineQuantity> medicineForOrder;

	public Errand() {
		super();
	}



	public PharmacyAdmin getAdmin() {
		return admin;
	}



	public void setAdmin(PharmacyAdmin admin) {
		this.admin = admin;
	}



	public Errand(Long id, Date deadline, Date creationTime, Pharmacy pharmacy, PharmacyAdmin admin,
			Boolean finished, List<MedicineQuantity> medicineForOrder) {
		super();
		this.id = id;
		this.deadline = deadline;
		this.creationTime = creationTime;
		this.pharmacy = pharmacy;
		this.admin = admin;
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
