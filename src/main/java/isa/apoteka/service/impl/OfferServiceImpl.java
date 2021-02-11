package isa.apoteka.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import isa.apoteka.domain.*;
import isa.apoteka.dto.MedicineQuantityPreviewDTO;
import isa.apoteka.dto.OfferDTO;
import isa.apoteka.dto.OfferPreviewDTO;
import isa.apoteka.repository.ErrandRepository;
import isa.apoteka.repository.SupplierMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isa.apoteka.async.service.EmailService;
import isa.apoteka.domain.Errand;
import isa.apoteka.domain.Offer;
import isa.apoteka.domain.PharmacyAdmin;
import isa.apoteka.dto.SupplierDTO;
import isa.apoteka.repository.ErrandRepository;
import isa.apoteka.repository.OfferRepository;
import isa.apoteka.service.ErrandService;
import isa.apoteka.service.MedicineQuantityService;
import isa.apoteka.service.OfferService;

@Service
@Transactional(readOnly = true)
public class OfferServiceImpl implements OfferService{
	
	private OfferRepository offerRepository;
	private EmailService emailService;
	private ErrandRepository errandRepository;
	private MedicineQuantityService medQuantityService;
	private SupplierMedicineRepository supplierMedicineRepository;

	@Autowired
	public OfferServiceImpl(OfferRepository offerRepository, EmailService emailService, ErrandRepository errandRepository, MedicineQuantityService medQuantityService, SupplierMedicineRepository supplierMedicineRepository) {
		this.offerRepository = offerRepository;
		this.emailService = emailService;
		this.errandRepository = errandRepository;
		this.medQuantityService = medQuantityService;
		this.supplierMedicineRepository =supplierMedicineRepository;
	}   

	@Override
	public List<SupplierDTO> findAllOffersForErrand(Long errandId) {
		List<Offer> offers = offerRepository.findAllOffersForErrand(errandId);
		List<SupplierDTO> dtos = new ArrayList<SupplierDTO>();		
		for(Offer o : offers) {
			dtos.add(new SupplierDTO(o.getSupplier().getId(),o.getId(),o.getSupplier().getFirstName(),o.getSupplier().getLastName(),o.getPrice(),o.getSupplyDeadline(), o.getIsApproved()));
		}
		
		return dtos;
	}

	@Override
	@Transactional(readOnly = false)
	public Boolean approveOffer(Long offerid, Long errandId) {
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Errand errand = errandRepository.findById(errandId).orElse(null);
		if(errand == null) {
			return false;
		}else if(!errand.getAdmin().getId().equals(admin.getId())) {
			return false;
		}
		  
		System.out.println(errandId);
		offerRepository.finishErrand(errandId);
		medQuantityService.changeQuantity(errandId);
		List<Offer> offers =  offerRepository.findAllOffersForErrand(errandId);
		for(Offer o : offers) {
			if(o.getId().equals(offerid)) {
				offerRepository.offerApproval(offerid);
			}
		}
		return true;
	}

	@Override
	@Transactional(readOnly = false)
	public void createOffer(OfferDTO offerDTO) throws Exception {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Errand errand = errandRepository.getOne(offerDTO.getErrandId());

		if(errand.getDeadline().before(new Date()))
			throw new Exception("Deadline passed");

		Offer existingOffer = offerRepository.findByErrandIdAndSupplierId(errand.getId(), supplier.getId());

		if(existingOffer != null) {
			if(existingOffer.getIsApproved() != null && existingOffer.getIsApproved())
				throw new Exception("Can not modify approved offer");
			existingOffer.setPrice(offerDTO.getPrice());
			offerRepository.save(existingOffer);
			return;
		}

		List<SupplierMedicine> supplierMedicines = supplierMedicineRepository.findAllBySupplierId(supplier.getId());
		Map<Long, Integer> medicineQuantity = new HashMap<Long, Integer>();
		for(SupplierMedicine medicine : supplierMedicines)
			medicineQuantity.put(medicine.getId(), medicine.getQuantity());

		for(MedicineQuantity medicine : errand.getMedicineForOrder())
			if (medicineQuantity.get(medicine.getMedicine().getId()) < medicine.getQuantity())
				throw new Exception("Not enought quantity");


		Offer offer = new Offer(errand, supplier, offerDTO.getPrice(), offerDTO.getSupplyDeadline());
		offerRepository.save(offer);
	}

	@Override
	public List<OfferPreviewDTO> getSuplierOffers() {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return offerRepository.findAllBySupplierId(supplier.getId()).stream().map(o -> new OfferPreviewDTO(o, o.getErrand().getPharmacy().getName())).collect(Collectors.toList());
	}

	@Override
	public List<MedicineQuantityPreviewDTO> getSupplierStock() {
		Supplier supplier = (Supplier) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return supplierMedicineRepository.findAllBySupplierId(supplier.getId()).stream().map(m -> new MedicineQuantityPreviewDTO(m.getMedicine().getId(), m.getMedicine().getName(), m.getQuantity())).collect(Collectors.toList());
	}

	@Override
	public Boolean sendMail(Long errandId) {
		List<Offer> offers1 =  offerRepository.findAllOffersForErrand(errandId);
		for(Offer o : offers1) {
			System.out.println(o);
			System.out.println(o.getIsApproved());
			emailService.sendOfferNotificaitionAsync(o);
		}
		
		return true;  
	}

}
