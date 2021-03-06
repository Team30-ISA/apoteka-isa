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

import com.fasterxml.jackson.annotation.JsonIgnore;
import isa.apoteka.dto.MedicineCreateDTO;

@Entity
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DrugType type;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DrugForm form;
	@Column
	private String contraindications;
	@Column
	private String composition;
	@Column
	private String recommendedIntakePerDay;
	@Column(name = "code", unique=true, nullable = true)
    private Long code;

	@JsonIgnore
	@ManyToMany
    @JoinTable(name = "medicine_substitutes",
            joinColumns = @JoinColumn(name = "medicine_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "sub_medicine_id", referencedColumnName = "id"))
	private List<Medicine> substitutes;
	@Column
	private String manufacturer;
	@Column
	private DrugIssuanceRegime regime;
	@Column
	private String notes;
	@Column
	private Double price;
	@Column
	private Double priceWithLoyalty;
	@Column
	private Integer loyaltyPoints;
	@JsonIgnore
	@OneToMany(mappedBy = "medicine")
	private List<MedicineInPharmacy> medicineInpharmacy;
	
	@JsonIgnore
	@OneToMany(mappedBy = "medicine")
	private List<ReservedMedicine> reservedMedicine;
	
	
	public Medicine() {
		super();
	}


	public Medicine(Long id, String name, DrugType type, DrugForm form, String contraindications, String composition,
			String recommendedIntakePerDay, List<Medicine> substitutes, String manufacturer, DrugIssuanceRegime regime,
			String notes, List<MedicineInPharmacy> medicineInpharmacy) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.form = form;
		this.contraindications = contraindications;
		this.composition = composition;
		this.recommendedIntakePerDay = recommendedIntakePerDay;
		this.substitutes = substitutes;
		this.manufacturer = manufacturer;
		this.regime = regime;
		this.notes = notes;
		this.medicineInpharmacy = medicineInpharmacy;
	}

	public Medicine(MedicineCreateDTO medicineDTO, List<Medicine> substitutes, DrugType drugType, DrugForm drugForm) {
		this.substitutes = substitutes;
		this.type = drugType;
		this.form = drugForm;
		this.name = medicineDTO.getName();
		this.composition = medicineDTO.getComposition();
		this.contraindications = medicineDTO.getContraindications();
		this.recommendedIntakePerDay = medicineDTO.getRecommendedIntakePerDay();
		this.manufacturer = medicineDTO.getManufacturer();
		this.loyaltyPoints = medicineDTO.getLoyaltyPoints();
		this.code = medicineDTO.getCode();
	}

	public Medicine(Long id, String name, Long code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public DrugType getType() {
		return type;
	}


	public DrugForm getForm() {
		return form;
	}


	public String getContraindications() {
		return contraindications;
	}


	public String getComposition() {
		return composition;
	}


	public String getRecommendedIntakePerDay() {
		return recommendedIntakePerDay;
	}


	public List<Medicine> getSubstitutes() {
		return substitutes;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public DrugIssuanceRegime getRegime() {
		return regime;
	}


	public String getNotes() {
		return notes;
	}


	public List<MedicineInPharmacy> getMedicineInpharmacy() {
		return medicineInpharmacy;
	}


	public Long getCode() {
		return code;
	}


	public void setCode(Long code) {
		this.code = code;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setType(DrugType type) {
		this.type = type;
	}


	public void setForm(DrugForm form) {
		this.form = form;
	}


	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}


	public void setComposition(String composition) {
		this.composition = composition;
	}


	public void setRecommendedIntakePerDay(String recommendedIntakePerDay) {
		this.recommendedIntakePerDay = recommendedIntakePerDay;
	}


	public void setSubstitutes(List<Medicine> substitutes) {
		this.substitutes = substitutes;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public void setRegime(DrugIssuanceRegime regime) {
		this.regime = regime;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public void setMedicineInpharmacy(List<MedicineInPharmacy> medicineInpharmacy) {
		this.medicineInpharmacy = medicineInpharmacy;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Double getPriceWithLoyalty() {
		return priceWithLoyalty;
	}


	public void setPriceWithLoyalty(Double priceWithLoyalty) {
		this.priceWithLoyalty = priceWithLoyalty;
	}


	public Integer getLoyaltyPoints() {
		return loyaltyPoints;
	}


	public void setLoyaltyPoints(Integer loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	
}
