package isa.apoteka;

import static isa.constants.PharmacyConstants.MEDICINE_ID;
import static isa.constants.PharmacyConstants.*;
import static isa.constants.PharmacyConstants.PHARMACY_ID;
import static isa.constants.PharmacyConstants.PATIENT_ID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import isa.apoteka.repository.MedicineRepository;
import isa.apoteka.service.MedicineService;
import isa.apoteka.service.impl.MedicineServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicineServiceTest {

	@Mock
	private MedicineRepository medicineRepositoryMock;
	
	@InjectMocks
	private MedicineServiceImpl medicineService;
	
	@Test
	public void testGetQuantityOfMedicineInPharmacy() {
		when(medicineRepositoryMock.getQuantityOfMedicineInPharmacy(PHARMACY_ID, MEDICINE_ID)).thenReturn(MEDICINE_QUANTITY);

		Boolean result = medicineService.isMedicineAvailableInPharmacy(PHARMACY_ID, MEDICINE_ID);

		assertTrue(result);
	}
	
	@Test
	public void testGetQuantityOfMedicineInPharmacyZero() {
		when(medicineRepositoryMock.getQuantityOfMedicineInPharmacy(PHARMACY_ID, MEDICINE_ID)).thenReturn(ZERO_QUANTITY);

		Boolean result = medicineService.isMedicineAvailableInPharmacy(PHARMACY_ID, MEDICINE_ID);

		assertFalse(result);
	}
	
	@Test
	public void testGetQuantityOfMedicineInPharmacyNull() {
		when(medicineRepositoryMock.getQuantityOfMedicineInPharmacy(PHARMACY_ID, MEDICINE_ID)).thenReturn(NULL_QUANTITY);

		Boolean result = medicineService.isMedicineAvailableInPharmacy(PHARMACY_ID, MEDICINE_ID);

		assertFalse(result);
	}
	
	@Test
	public void testIsPatientAllergic() {
		when(medicineRepositoryMock.isPatientAllergic(PATIENT_ID, MEDICINE_ID)).thenReturn(PATIENT_ALLERGY);
		
		Boolean result = medicineService.isPatientAllergic(PATIENT_ID, MEDICINE_ID);
		
		assertTrue(result);
	}
	
	@Test
	public void testIsPatientAllergicFalse() {
		when(medicineRepositoryMock.isPatientAllergic(PATIENT_ID, MEDICINE_ID)).thenReturn(ZERO_ALLERGY);
		
		Boolean result = medicineService.isPatientAllergic(PATIENT_ID, MEDICINE_ID);
		
		assertFalse(result);
	}
		
		
}
