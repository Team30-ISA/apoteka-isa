package isa.apoteka.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class MedicinePrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Medicine medicine;
	
	@ManyToOne
	private Pharmacy pharmacy;
	
	@Column
	private Integer price;
	
	@Column
	private Date startOfPrice;
	
	@Column
	private Date endOfPrice;

	@Column(name = "quantity", nullable = true)
    private Integer quantity;
	
	public MedicinePrice() {
		super();
	}

	public MedicinePrice(Long id, Medicine medicine, Pharmacy pharmacy, Integer price, Date startOfPrice,
			Date endOfPrice) {
		super();
		this.id = id;
		this.medicine = medicine;
		this.pharmacy = pharmacy;
		this.price = price;
		this.startOfPrice = startOfPrice;
		this.endOfPrice = endOfPrice;
	}

	public MedicinePrice(Long id, Medicine medicine, Pharmacy pharmacy, Integer price, Date startOfPrice,
			Date endOfPrice, Integer quantity) {
		this.id = id;
		this.medicine = medicine;
		this.pharmacy = pharmacy;
		this.price = price;
		this.startOfPrice = startOfPrice;
		this.endOfPrice = endOfPrice;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getStartOfPrice() {
		return startOfPrice;
	}

	public void setStartOfPrice(Date startOfPrice) {
		this.startOfPrice = startOfPrice;
	}

	public Date getEndOfPrice() {
		return endOfPrice;
	}

	public void setEndOfPrice(Date endOfPrice) {
		this.endOfPrice = endOfPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	
}
