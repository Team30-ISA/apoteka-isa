package isa.apoteka.dto;

import isa.apoteka.domain.Dermatologist;
import isa.apoteka.domain.Pharmacist;
import isa.apoteka.domain.Pharmacy;
import isa.apoteka.domain.UserRequest;

import java.util.List;
import java.util.stream.Collectors;

public class ComplaintsListsDTO {
    private List<UserRequest> dermatologists;
    private List<UserRequest> pharmacists;
    private List<PharmacyDTO> pharmacies;

    public ComplaintsListsDTO() {
    }


    public ComplaintsListsDTO(List<Pharmacist> pharmacistst, List<Pharmacy> pharmacies, List<Dermatologist> dermatologists) {
        this.pharmacists = pharmacistst.stream().map(p -> new UserRequest(p)).collect(Collectors.toList());
        this.dermatologists = dermatologists.stream().map(d -> new UserRequest(d)).collect(Collectors.toList());
        this.pharmacies = pharmacies.stream().map(d -> new PharmacyDTO(d)).collect(Collectors.toList());
    }

    public List<UserRequest> getDermatologists() {
        return dermatologists;
    }

    public void setDermatologists(List<UserRequest> dermatologists) {
        this.dermatologists = dermatologists;
    }

    public List<UserRequest> getPharmacists() {
        return pharmacists;
    }

    public void setPharmacists(List<UserRequest> pharmacists) {
        this.pharmacists = pharmacists;
    }

    public List<PharmacyDTO> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(List<PharmacyDTO> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
