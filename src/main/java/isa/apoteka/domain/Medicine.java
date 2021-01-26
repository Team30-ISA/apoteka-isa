package isa.apoteka.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	String name;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DrugType type;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DrugForm form;
	@Column
	String contraindications;
	@Column
	String composition;
	@Column
	String recommendedIntakePerDay;
	@ManyToMany
    @JoinTable(name = "medicine_substitutes",
            joinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sub_medicine_id", referencedColumnName = "id"))
	List<Medicine> substitutes;
	@Column
	String manufacturer;
	@Column
	DrugIssuanceRegime regime;
	@Column
	String notes;
	
	@OneToMany(mappedBy = "medicine")
	private List<MedicineInPharmacy> medicineInpharmacy;
	
	

	public Medicine() {
		super();
	}

	public Medicine(Long id, String name, List<MedicineInPharmacy> medicineInpharmacy) {
		this.id = id;
		this.name = name;
		this.medicineInpharmacy = medicineInpharmacy;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<MedicineInPharmacy> getMedicineInpharmacy() {
		return medicineInpharmacy;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMedicineInpharmacy(List<MedicineInPharmacy> medicineInpharmacy) {
		this.medicineInpharmacy = medicineInpharmacy;
	}
	
}
