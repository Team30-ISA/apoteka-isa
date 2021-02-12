package isa.apoteka.dto;

public class ComplaintResponseDTO {
    private String response;
    private Long complaintId;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }
}
