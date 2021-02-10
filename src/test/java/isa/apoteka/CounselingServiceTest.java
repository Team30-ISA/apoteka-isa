package isa.apoteka;

import static isa.constants.CounselingConstants.COUNSELING;
import static isa.constants.CounselingConstants.COUNSELING3;
import static isa.constants.CounselingConstants.COUNSELING4;
import static isa.constants.CounselingConstants.COUNSELING_COUNT;
import static isa.constants.CounselingConstants.DERMATOLOGIST_ID;
import static isa.constants.CounselingConstants.DWC_1_END_DATE;
import static isa.constants.CounselingConstants.DWC_1_START_DATE;
import static isa.constants.CounselingConstants.NOW;
import static isa.constants.CounselingConstants.NOW_MINUS_1;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import isa.apoteka.domain.Counseling;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.repository.CounselingRepository;
import isa.apoteka.service.impl.CounselingServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CounselingServiceTest {

	@Mock
	private CounselingRepository counselingRepositoryMock;

	@InjectMocks
	private CounselingServiceImpl counselingService;

	// Funckija trazi termin koji nije zauzet, a koji je najblizi prosledjenom
	// datumu
	// Ishod: nije pronadjen ni jedan termin
	@Test
	public void testGetNearestCounselingNotFound() {
		when(counselingRepositoryMock.findAllByDermAndStart(DERMATOLOGIST_ID, NOW_MINUS_1)).thenReturn(Arrays.asList());

		Counseling counseling = counselingService.getNearestCounseling(DERMATOLOGIST_ID, NOW, true);

		assertNull(counseling);

	}

	// Funckija trazi termin koji nije zauzet, a koji je najblizi prosledjenom
	// datumu
	// Ishod: pronadjen je termin
	@Test
	public void testGetNearestCounselingFound() {
		when(counselingRepositoryMock.findAllByDermAndStart(DERMATOLOGIST_ID, NOW_MINUS_1))
				.thenReturn(Arrays.asList(COUNSELING3, COUNSELING4));

		Counseling counseling = counselingService.getNearestCounseling(DERMATOLOGIST_ID, NOW, true);

		assertEquals(3L, counseling.getId());

	}

	// Funckija izracunava broj termina po danu pocevsi od zadatog datuma, pa za
	// onoliko dana za koliko je podesen poslednji parametar(broj_dana)
	// Ishod: prebrojani termini za jedan dan
	@Test
	public void testCountAllTermsByDays() {
		when(counselingRepositoryMock.countAllTerms(DERMATOLOGIST_ID, DWC_1_START_DATE, DWC_1_END_DATE))
				.thenReturn(COUNSELING_COUNT);

		List<Long> list = counselingService.countAllTermsByDays(DERMATOLOGIST_ID, DWC_1_START_DATE, 1);

		assertEquals(1, list.size());
		assertEquals(4L, list.get(0));

	}

	// Funckija mapira Counseling na ExaminationDTO
	// Scenario uspesnog mapiranja
	@Test
	public void testMapCounselingToCounselingDTO() {
		ExaminationDTO dto = counselingService.mapCounselingToExaminationDTO(COUNSELING);

		assertEquals(dto.getId(), COUNSELING.getId());
		assertEquals(dto.getDuration(), COUNSELING.getDuration());
		assertEquals(dto.getPatientName(),
				COUNSELING.getPatient().getFirstName() + " " + COUNSELING.getPatient().getLastName());
		assertEquals(dto.getPharmacyName(), COUNSELING.getDermatologistWorkCalendar().getPharmacy().getName());
		assertEquals(dto.getPrice(), COUNSELING.getPrice());
		assertEquals(dto.getReport(), COUNSELING.getReport());
		assertEquals(dto.getStartDate(), COUNSELING.getStartDate());

	}

	// Funckija mapira null na ExaminationDTO
	// Scenario (ne)uspesnog mapiranja
	@Test
	public void testMapCounselingToCounselingDTONull() {
		ExaminationDTO dto = counselingService.mapCounselingToExaminationDTO(null);

		assertNull(dto);

	}

}
