package isa.apoteka.dto;

import isa.apoteka.domain.Medicine;

import java.util.List;
import java.util.stream.Collectors;

public class MedicineSpecificationDTO {
    private String contraindications;
    private String composition;
    private String recomendedIntake;
    private List<String> substitutes;

    public MedicineSpecificationDTO() {
    }

    public MedicineSpecificationDTO(Medicine medicine) {
        this.contraindications = medicine.getContraindications();
        this.composition = medicine.getComposition();
        this.recomendedIntake = medicine.getRecommendedIntakePerDay();
        this.substitutes = medicine.getSubstitutes().stream().map(s -> s.getName()).collect(Collectors.toList());
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getRecomendedIntake() {
        return recomendedIntake;
    }

    public void setRecomendedIntake(String recomendedIntake) {
        this.recomendedIntake = recomendedIntake;
    }

    public List<String> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<String> substitutes) {
        this.substitutes = substitutes;
    }
}
