package isa.apoteka.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;


@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long country_id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private Set<City> cities = new HashSet<City>();
	
	public Country() {
		super();
	}
	public Long getCountryId() {
		return country_id;
	}
	public void setCountryId(Long country_id) {
		this.country_id = country_id;
	}
	public String getName() {
		return name;
	}
	public Country(Long country_id, String name) {
		super();
		this.country_id = country_id;
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<City> getCities() {
		return cities;
	}
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Country c = (Country) o;
		if (c.country_id == null || country_id == null) {
			return false;
		}
		return Objects.equals(country_id, c.country_id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(country_id);
	}
}
