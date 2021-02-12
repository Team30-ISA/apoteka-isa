package isa.apoteka.service;

import isa.apoteka.dto.ComplaintDTO;
import isa.apoteka.dto.ComplaintResponseDTO;

import java.util.List;

public interface ComplaintService {
    ComplaintDTO create(ComplaintDTO complaintDTO) throws Exception;

    List<ComplaintDTO> getAllComplaints();

    ComplaintResponseDTO respond(ComplaintResponseDTO complaintResponseDTO);
}
