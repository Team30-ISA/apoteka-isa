package isa.apoteka.dto;

import isa.apoteka.domain.ComplaintUser;

public class ComplaintDTO {
    private Long recipient;
    private ComplaintUser complaintUser;
    private String message;

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
}
