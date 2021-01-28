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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Address")
@Inheritance(strategy=TABLE_PER_CLASS)
public class Address {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "mySeqGenV3", sequenceName = "mySeqV3", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV3")
    @Column(name = "id", unique=true, nullable=false)
    private Long id;

    @Column(name = "Street")
    private String street;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;
    
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
