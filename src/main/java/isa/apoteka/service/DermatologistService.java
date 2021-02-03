package isa.apoteka.service;

import java.util.List;
import isa.apoteka.domain.Dermatologist;
import isa.apoteka.dto.FilteredDermDTO;
import isa.apoteka.dto.PharmacyDTO;
import isa.apoteka.dto.SearchDermFilterDTO;

public interface DermatologistService {
	String getLogged();
	Dermatologist findById(Long id);
	Dermatologist findByName(String name);
	void hireDerm(Long dermId, Long pharmacyId);
	void fireDerm(Long dermId, Long pharmacyId);
	List<PharmacyDTO> getDermPharmacies(Long dermatologistId);
	void update(String firstName, String lastName, Long id);
	List<Dermatologist> findAll();
	List<FilteredDermDTO> searchDerms(SearchDermFilterDTO derms);
}
