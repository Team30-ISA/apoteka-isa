package isa.apoteka.domain;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Table(name="Country")
@Inheritance(strategy=TABLE_PER_CLASS)
public class Country {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique=true, nullable=false)
    private Long id;

    @Column(name = "Country")
    private String country;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country ) {
        this.country = country;
    }
    
    @Override
	public String toString() {
		return country;
	}
}