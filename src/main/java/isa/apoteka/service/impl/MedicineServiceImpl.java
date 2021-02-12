package isa.apoteka.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import isa.apoteka.domain.*;
import isa.apoteka.dto.*;
import isa.apoteka.repository.*;
import isa.apoteka.dto.FilteredMedicineDTO;
import isa.apoteka.dto.MedicineCreateDTO;
import isa.apoteka.repository.DrugFormRepository;
import isa.apoteka.repository.DrugTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import isa.apoteka.dto.MedicineDTO;
import isa.apoteka.dto.MedicineNameDTO;
import isa.apoteka.dto.SearchFilterMedicineDTO;
import isa.apoteka.repository.MedicineRepository;
import isa.apoteka.service.MedicineInPharmacyService;
import isa.apoteka.service.MedicinePriceService;
import isa.apoteka.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {
	
	
	private MedicineRepository medicineRepository;
	private MedicinePriceService medPriceService;
	private MedicineInPharmacyService medInPharmacyService; 

	@Autowired
	public MedicineServiceImpl(MedicineRepository medicineRepository, MedicinePriceService medPriceService, MedicineInPharmacyService medInPharmacyService) {
		this.medicineRepository = medicineRepository;
		this.medPriceService = medPriceService;
		this.medInPharmacyService = medInPharmacyService;
	}

	@Autowired
	private DrugFormRepository drugFormRepository;

	@Autowired
	private DrugTypeRepository drugTypeRepository;

	@Autowired
	private MedicineInPharmacyRepository medicineInPharmacyRepository;

	@Autowired
	private MedicinePriceRepository medicinePriceRepository;

	@Override
	public List<Medicine> findAll() {
		return medicineRepository.findAll();
	}

	@Override
	public List<Medicine> searchMedicinesByName(String name) {
		return medicineRepository.searchMedicinesByName(name.toLowerCase());
	}

	@Override
	public Boolean isPatientAllergic(Long patientId, Long medicineId) {
		if(medicineRepository.isPatientAllergic(patientId, medicineId) > 0)
			return true;
		return false;
	}

	@Override
	public List<Medicine> getSubstitutesOfMedicine(Long id) {
		return medicineRepository.getSubstitutesOfMedicine(id);
	}

	@Override
	public Boolean isMedicineAvailableInPharmacy(Long medicineId, Long pharmacyId) {
		Integer quantity = medicineRepository.getQuantityOfMedicineInPharmacy(medicineId, pharmacyId);
		if(quantity == null)
			return false;
		if(quantity > 0)
			return true;
		return false;
	}

	@Override
	public Medicine findOne(Long id) {
		return medicineRepository.findById(id).orElse(null);
	}

	@Override
	public List<MedicineDTO> findAllMedicineInPharmacy() {
		List<MedicineDTO> dto = new ArrayList<MedicineDTO>();
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MedicineInPharmacy> medicineInPharmacy = medInPharmacyService.getMedicineInPharmacy(admin.getPharmacy().getId());
		
		for(MedicineInPharmacy m : medicineInPharmacy) { 			
			Long medId = m.getMedicine().getId();
			String name = m.getMedicine().getName();
			int quantity = m.getQuantity();
			MedicinePrice medPrice = medPriceService.findMedicinePrice(admin.getPharmacy().getId(), medId);
			int price;
			if(medPrice.getPrice() == null) {
				price = 0;
			}else {
				price = medPrice.getPrice();
			}
			Date start = medPrice.getStartOfPrice();
			Date end = medPrice.getEndOfPrice();
			dto.add(new MedicineDTO(medId, name, quantity, price, start,end ));
		}
		
		return dto;
	}

	@Override
	public List<MedicineDTO> searchMedicineInPharmacy(String name) {
		List<MedicineDTO> dto = new ArrayList<MedicineDTO>();
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MedicineInPharmacy> medicineInPharmacy = medInPharmacyService.searchMedicineInPharmacy(admin.getPharmacy().getId(), name);
		
		for(MedicineInPharmacy m : medicineInPharmacy) { 			
			Long medId = m.getMedicine().getId();
			String medName = m.getMedicine().getName();
			int quantity = m.getQuantity();
			MedicinePrice medPrice = medPriceService.findMedicinePrice(admin.getPharmacy().getId(), medId);
			int price;
			if(medPrice.getPrice() == null) {
				price = 0;
			}else {
				price = medPrice.getPrice();
			}
			Date start = medPrice.getStartOfPrice();
			Date end = medPrice.getEndOfPrice();
			dto.add(new MedicineDTO(medId, medName, quantity, price, start,end ));
		}
		
		return dto;
	}

	@Override
	public List<MedicineNameDTO> findAllMedicineNotInPharmacy() {
		
		PharmacyAdmin admin = (PharmacyAdmin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Medicine> med = medicineRepository.findAllMedicineNotInPharmacy(admin.getPharmacy().getId());
		List<MedicineNameDTO> dto = new ArrayList<MedicineNameDTO>();
			for(Medicine m : med) {
				dto.add(new MedicineNameDTO(m.getId(),m.getName()));
			}
			
		return dto;
	}

	@Override
	public List<MedicineNameDTO> findAllMedicine() {
		List<Medicine> med = medicineRepository.findAll();
		List<MedicineNameDTO> dto = new ArrayList<MedicineNameDTO>();
			for(Medicine m : med) {
				dto.add(new MedicineNameDTO(m.getId(),m.getName()));
			}
			
		return dto;
	}

	@Override
	public Medicine create(MedicineCreateDTO medicineDTO) {
		DrugForm drugForm = drugFormRepository.getOne(medicineDTO.getForm());
		DrugType drugType = drugTypeRepository.getOne(medicineDTO.getType());
		List<Medicine> substitutes = medicineRepository.findAllById(medicineDTO.getSubstitutes());
		Medicine medicine = new Medicine(medicineDTO, substitutes, drugType, drugForm);
		medicineRepository.save(medicine);
		return medicine;
	}

	@Override
	public List<DrugType> getAllTypes() {
		return drugTypeRepository.findAll();
	}

	@Override
	public List<DrugForm> getAllForms() {
		return drugFormRepository.findAll();
	}
	
	public List<MedicineDTO> findAllMedicineAvailableInPharmacy(Long pharmacyId) {
		List<MedicineDTO> dto = new ArrayList<MedicineDTO>();
		List<MedicineInPharmacy> medicineInPharmacy = medInPharmacyService.getAvailableMedicineInPharmacy(pharmacyId);
		System.out.println("*****************");
		System.out.println(medicineInPharmacy.size());
		for(MedicineInPharmacy m : medicineInPharmacy) { 			
			Long medId = m.getMedicine().getId();
			String name = m.getMedicine().getName();
			int quantity = m.getQuantity();
			MedicinePrice medPrice = medPriceService.findMedicinePrice(pharmacyId, medId);
			int price;
			Date start;
			Date end;
			if(medPrice == null) {
				price = 0;
				start = null;
				end = null;
			}else {
				price = medPrice.getPrice();
				start = medPrice.getStartOfPrice();
				end = medPrice.getEndOfPrice();
			}
			
			dto.add(new MedicineDTO(medId, name, quantity, price, start,end ));
		}
		
		return dto;
	}

	@Override
	public List<MedicinePreviewDTO> getAllMedicines(String medicineName) {
		List<Medicine> medicines = medicineRepository.findByNameContaining(medicineName);
		List<Long> ids = medicines.stream().map(m -> m.getId()).collect(Collectors.toList());
		List<MedicineInPharmacy> medicineInPharmacies = medicineInPharmacyRepository.findByMedicineIdIn(ids);
		List<MedicinePrice> medicinePrices = medicinePriceRepository.findByMedicineIdIn(ids);

		List<MedicinePreviewDTO> medicinePreviewDTOList = new ArrayList<>();
		for(MedicineInPharmacy medicine : medicineInPharmacies) {
			for(MedicinePrice medicinePrice : medicinePrices) {
				if(medicine.getMedicine().getId().equals(medicinePrice.getMedicine().getId()) && medicine.getPharmacy().getId().equals(medicinePrice.getPharmacy().getId())) {
					medicinePreviewDTOList.add(new MedicinePreviewDTO(medicine.getMedicine(), medicine.getPharmacy(), medicine.getQuantity(), medicinePrice.getPrice()));
					continue;
				}
			}
		}

		return medicinePreviewDTOList;
	}

	@Override
	public MedicineSpecificationDTO getMedicine(Long id) {
		Medicine medicine = medicineRepository.getOne(id);
		return new MedicineSpecificationDTO(medicine);
	}
	public List<FilteredMedicineDTO> searchMedicineByName(SearchFilterMedicineDTO medicineDTO) {
		List<FilteredMedicineDTO> dto = new ArrayList<FilteredMedicineDTO>();
		return dto;
	}

 

}
