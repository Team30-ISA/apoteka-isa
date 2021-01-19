package isa.apoteka.domain;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ADDRESSES")
public class Address implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long address_id;
	
	@Column(name = "street", nullable = false)
	private String street;
	
	@Column(name = "number", nullable = false)
	private int number;
	
	public Address(Long address_id, String street, int number) {
		super();
		this.address_id = address_id;
		this.street = street;
		this.number = number;
	}

	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false, unique = false),
		@JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false, unique = false)})
	private City city;
	
	
	public Address() {	
	}
	
	public Long getAddressId() {
		return address_id;
	}

	public void setAddressId(Long id) {
		this.address_id = id;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
		
}
