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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import isa.apoteka.dto.PharmacyDTO;

@Entity
public class Pharmacy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	@NotBlank
	String name;
	
	@Column(unique = true, nullable = false)
	@NotBlank
	String address;
	
	private String street;
	
	private String city;
	
	private String description;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pharmacy_dermatologist",
            joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<Dermatologist> dermatologists;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "promotion_notification",
            joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    private List<Patient> patients;

	@OneToMany(mappedBy = "medicine")
	private List<MedicineInPharmacy> medicineInpharmacy;
	
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Pharmacist> pharmacists;
	
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<PharmacyAdmin> pharmacyAdmins;
	
	@OneToMany(mappedBy = "pharmacy", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Promotion> promotions;

	public Pharmacy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pharmacy(Long id, String name, String street, String city, String description,
			List<Dermatologist> dermatologists, List<Patient> patients, List<MedicineInPharmacy> medicineInpharmacy,
			List<Pharmacist> pharmacists, List<PharmacyAdmin> pharmacyAdmins, List<Promotion> promotions) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.city = city;
		this.description = description;
		this.dermatologists = dermatologists;
		this.patients = patients;
		this.medicineInpharmacy = medicineInpharmacy;
		this.pharmacists = pharmacists;
		this.pharmacyAdmins = pharmacyAdmins;
		this.promotions = promotions;
	}

    public Pharmacy(PharmacyDTO pharmacyDTO) {
		this.name = pharmacyDTO.getName();
		this.address = pharmacyDTO.getAddress();
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Dermatologist> getDermatologists() {
		return dermatologists;
	}

	public void setDermatologists(List<Dermatologist> dermatologists) {
		this.dermatologists = dermatologists;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<MedicineInPharmacy> getMedicineInpharmacy() {
		return medicineInpharmacy;
	}

	public void setMedicineInpharmacy(List<MedicineInPharmacy> medicineInpharmacy) {
		this.medicineInpharmacy = medicineInpharmacy;
	}

	public List<Pharmacist> getPharmacists() {
		return pharmacists;
	}

	public void setPharmacists(List<Pharmacist> pharmacists) {
		this.pharmacists = pharmacists;
	}

	public List<PharmacyAdmin> getPharmacyAdmins() {
		return pharmacyAdmins;
	}

	public void setPharmacyAdmins(List<PharmacyAdmin> pharmacyAdmins) {
		this.pharmacyAdmins = pharmacyAdmins;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pharmacy other = (Pharmacy) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public List<PharmacyAdmin> getPharmacyAdmins() {
		return pharmacyAdmins;
	}

	public void setPharmacyAdmins(List<PharmacyAdmin> pharmacyAdmins) {
		this.pharmacyAdmins = pharmacyAdmins;
	}

	@Override
	public String toString() {
		return "Pharmacy [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	
}
