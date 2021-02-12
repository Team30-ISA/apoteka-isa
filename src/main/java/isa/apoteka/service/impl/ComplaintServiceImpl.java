package isa.apoteka.service.impl;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Complaint;
import isa.apoteka.domain.Patient;
import isa.apoteka.dto.ComplaintDTO;
import isa.apoteka.dto.ComplaintResponseDTO;
import isa.apoteka.dto.ComplaintsListsDTO;
import isa.apoteka.repository.ComplaintRepository;
import isa.apoteka.service.ComplaintService;
import isa.apoteka.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private EmailService emailService;

    @Override
    public ComplaintDTO create(ComplaintDTO complaintDTO) throws Exception {
        Patient p = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ComplaintsListsDTO complaintsLists = patientService.findAllEntitiesToComplain();

        switch (complaintDTO.getComplaintUser()) {
            case DERMATOLOGIST:
                if(!complaintsLists.getDermatologists().stream().anyMatch(d -> d.getId().equals(complaintDTO.getRecipient())))
                    throw new Exception("Not allowed");
                break;
            case PHARMACY:
                if(!complaintsLists.getPharmacies().stream().anyMatch(d -> d.getId().equals(complaintDTO.getRecipient())))
                    throw new Exception("Not allowed");
                break;
            case PHARMACIST:
                if(!complaintsLists.getPharmacists().stream().anyMatch(d -> d.getId().equals(complaintDTO.getRecipient())))
                    throw new Exception("Not allowed");
                break;
        }

        Complaint complaint = new Complaint(complaintDTO);
        complaint.setPatient(p);
        complaintRepository.save(complaint);
        return complaintDTO;
    }

    @Override
    public List<ComplaintDTO> getAllComplaints() {
        return complaintRepository.findByRespondFalse().stream().map(c -> new ComplaintDTO(c)).collect(Collectors.toList());
    }

    @Override
    public ComplaintResponseDTO respond(ComplaintResponseDTO complaintResponseDTO) {
        Complaint complaint = complaintRepository.getOne(complaintResponseDTO.getComplaintId());
        complaint.setRespond(true);
        complaintRepository.save(complaint);

        emailService.sendComplaintAnswer(complaint.getRecipientName(), complaintResponseDTO.getResponse(), complaint.getPatient().getEmail());

        return complaintResponseDTO;
    }
}
