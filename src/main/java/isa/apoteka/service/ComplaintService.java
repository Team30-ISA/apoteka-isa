package isa.apoteka.service;

import isa.apoteka.dto.ComplaintDTO;

public interface ComplaintService {
    ComplaintDTO create(ComplaintDTO complaintDTO) throws Exception;
}
