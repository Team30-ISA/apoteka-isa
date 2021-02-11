package isa.apoteka.service.impl;

import isa.apoteka.domain.Complaint;
import isa.apoteka.domain.Patient;
import isa.apoteka.dto.ComplaintDTO;
import isa.apoteka.dto.ComplaintsListsDTO;
import isa.apoteka.repository.ComplaintRepository;
import isa.apoteka.service.ComplaintService;
import isa.apoteka.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private PatientService patientService;

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
}
