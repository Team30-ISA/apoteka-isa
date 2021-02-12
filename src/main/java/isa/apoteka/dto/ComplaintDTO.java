package isa.apoteka.dto;

import isa.apoteka.domain.Complaint;
import isa.apoteka.domain.ComplaintUser;

public class ComplaintDTO {
    private Long id;
    private Long recipient;
    private String recipientName;
    private ComplaintUser complaintUser;
    private String message;


    public ComplaintDTO() {
    }

    public ComplaintDTO(Complaint c) {
        this.id = c.getId();
        this.recipient = c.getRecipient();
        this.recipientName = c.getRecipientName();
        this.message = c.getMessage();
        this.complaintUser = c.getComplaintUser();
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public ComplaintUser getComplaintUser() {
        return complaintUser;
    }

    public void setComplaintUser(ComplaintUser complaintUser) {
        this.complaintUser = complaintUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
