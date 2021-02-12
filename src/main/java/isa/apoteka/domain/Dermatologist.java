package isa.apoteka.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import isa.apoteka.dto.PharmacistDTO;

@Entity
@Table(name="dermatologist")
public class Dermatologist extends User{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4658541871043381050L;

	@JsonIgnore
	@ManyToMany(mappedBy = "dermatologists",cascade = CascadeType.MERGE)
	private List<Pharmacy> pharmacies;
	
	private int grade;
	
	private Date lastReqDate;

	public Dermatologist() {
		super();
		this.version = 0L;
	}
	
	public Dermatologist(Long id,String firstName, String lastName, Date lastReqDate) {
		super();  
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.lastReqDate = lastReqDate;
		this.version = 0L;
	}

    public Dermatologist(PharmacistDTO dermatologistData) {
		super(dermatologistData);
    }

    public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}


	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}
	
	public int getGrade() {
		return this.grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getLastReqDate() {
		return lastReqDate;
	}

	public void setLastReqDate(Date lastReqDate) {
		this.lastReqDate = lastReqDate;
	}
	
	
}
