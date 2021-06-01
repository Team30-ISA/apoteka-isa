package isa.apoteka.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="patient")
public class Patient extends User{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToMany(mappedBy = "patients")
	private List<Pharmacy> pharmacies;
	
	@OneToMany(mappedBy = "medicine")
	private List<ReservedMedicine> reservedMedicine;

	@JsonIgnore
	@ManyToMany
	private List<Medicine> allergies;

    @Column(name = "loyaltyCategory", nullable = true)
    private String loyaltyCategory;
    
    @Column(name = "points", nullable = true)
    private Integer points;
    
    @Column(name = "discount", nullable = true)
    private Double discount;
	 
	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}

	public Patient() {
		super();
	}
	
	public Patient(Long id,String firstName, String lastName) {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}

    public Patient(UserRequest userRequest) {
        super(userRequest);
    }

	public String getLoyaltyCategory() {
		return loyaltyCategory;
	}

	public void setLoyaltyCategory(String loyaltyCategory) {
		this.loyaltyCategory = loyaltyCategory;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}
