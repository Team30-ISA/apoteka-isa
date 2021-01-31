package isa.apoteka.domain;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.JOINED;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="City")
@Inheritance(strategy=TABLE_PER_CLASS)
public class City {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "mySeqGenV3", sequenceName = "mySeqV3", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mySeqGenV3")
    @Column(name = "id", unique=true, nullable=false)
    private Long id;

    @Column(name = "City")
    private String city;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
    
    
    
    public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(Long id) {
		this.id = id;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country ) {
        this.country = country;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city ) {
        this.city = city;
    }
    
    @Override
	public String toString() {
		return "City=" + city + ", Country=" + country.toString();
	}
}
