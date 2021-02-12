package isa.apoteka;

import static isa.constants.PharmacyConstants.MEDICINE_ID;
import static isa.constants.PharmacyConstants.MEDICINE_QUANTITY;
import static isa.constants.PharmacyConstants.PHARMACY_ID;
import static isa.constants.PharmacyConstants.MEDICINE_PRICE1;
import static isa.constants.PharmacyConstants.MEDICINE_PRICE2;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import isa.apoteka.domain.MedicinePrice;
import isa.apoteka.repository.MedicinePriceRepository;
import isa.apoteka.repository.MedicineRepository;
import isa.apoteka.service.impl.MedicinePriceServiceImpl;
import isa.apoteka.service.impl.MedicineServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicinePriceServiceTest {
	
	@Mock
	private MedicinePriceRepository medicinePriceRepositoryMock;
	
	@InjectMocks
	private MedicinePriceServiceImpl medicinePriceService;
		
	@Test
	public void test_findMedicinePrice_OnePrice() {
		when(medicinePriceRepositoryMock.findMedicinePrice(PHARMACY_ID, MEDICINE_ID)).thenReturn(Arrays.asList(MEDICINE_PRICE1));

		MedicinePrice medicinePrice = medicinePriceService.findMedicinePrice(PHARMACY_ID, MEDICINE_ID);

		assertNotNull(medicinePrice);
	}
	
	@Test
	public void test_findMedicinePrice_MorePrice() {
		when(medicinePriceRepositoryMock.findMedicinePrice(PHARMACY_ID, MEDICINE_ID)).thenReturn(Arrays.asList(MEDICINE_PRICE1, MEDICINE_PRICE2));

		MedicinePrice medicinePrice = medicinePriceService.findMedicinePrice(PHARMACY_ID, MEDICINE_ID);

		assertNotNull(medicinePrice);
	}
	
	
	@Test
	public void test_findMedicinePriceNotFound() {
		when(medicinePriceRepositoryMock.findMedicinePrice(PHARMACY_ID, MEDICINE_ID)).thenReturn(null);

		MedicinePrice medicinePrice = medicinePriceService.findMedicinePrice(PHARMACY_ID, MEDICINE_ID);

		assertNull(medicinePrice);
	}
}
