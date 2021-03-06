package isa.apoteka.domain;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Address")
@Inheritance(strategy=TABLE_PER_CLASS)
public class Address {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Street")
    private String street;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    public Address() {
    }

    public Address(String address, City city) {
        this.city = city;
        this.street = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city ) {
        this.city = city;
    }
    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    @Override
	public String toString() {
		return "Address=" + street + city.toString();
	}
}
