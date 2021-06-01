package isa.apoteka.dto;

public class PharmacyMedicineAvailabilityDTO {
	private Long pharmacyId;
    private String pharmacyName;
    private String city;
    private String street;
    private double price;
    private double priceWithLoyalty;
    private double grade;

    public PharmacyMedicineAvailabilityDTO() { }

    public PharmacyMedicineAvailabilityDTO(Long pharmacyId,  String pharmacyName, String city, String street, double price, double priceWithLoyalty,double grade) {
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
        this.city = city;
        this.street = street;
        this.price = price;
        this.priceWithLoyalty = priceWithLoyalty;
        this.grade = grade;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmId) {
        pharmacyId = pharmId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public double getPriceWithLoyalty() {
		return priceWithLoyalty;
	}

	public void setPriceWithLoyalty(double priceWithLoyalty) {
		this.priceWithLoyalty = priceWithLoyalty;
	}
}
