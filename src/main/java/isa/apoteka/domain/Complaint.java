package isa.apoteka.domain;

import isa.apoteka.dto.ComplaintDTO;

import javax.persistence.*;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long recipient;
    private String recipientName;
    private String message;
    private ComplaintUser complaintUser;
    private Boolean respond = false;

    @ManyToOne
    private Patient patient;

    public Complaint() {
    }

    public Complaint(ComplaintDTO complaintDTO) {
        this.recipient = complaintDTO.getRecipient();
        this.message = complaintDTO.getMessage();
        this.complaintUser = complaintDTO.getComplaintUser();
        this.recipientName = complaintDTO.getRecipientName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ComplaintUser getComplaintUser() {
        return complaintUser;
    }

    public void setComplaintUser(ComplaintUser complaintUser) {
        this.complaintUser = complaintUser;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Boolean getRespond() {
        return respond;
    }

    public void setRespond(Boolean respond) {
        this.respond = respond;
    }
}
