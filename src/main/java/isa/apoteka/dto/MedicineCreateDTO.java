package isa.apoteka.dto;
import java.util.List;

public class MedicineCreateDTO {
    private Long id;
    private String name;
    private Long type;
    private Long form;
    private String contraindications;
    private String composition;
    private String recommendedIntakePerDay;
    private List<Long> substitutes;
    private String manufacturer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getForm() {
        return form;
    }

    public void setForm(Long form) {
        this.form = form;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contradictions) {
        this.contraindications = contradictions;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getRecommendedIntakePerDay() {
        return recommendedIntakePerDay;
    }

    public void setRecommendedIntakePerDay(String recommendedIntakePerDay) {
        this.recommendedIntakePerDay = recommendedIntakePerDay;
    }

    public List<Long> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Long> substitutes) {
        this.substitutes = substitutes;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
