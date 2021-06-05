package isa.apoteka;

import static isa.constants.ExaminationConstants.EXAMINATION;
import static isa.constants.ExaminationConstants.EXAMINATION3;
import static isa.constants.ExaminationConstants.EXAMINATION4;
import static isa.constants.ExaminationConstants.EXAMINATION_COUNT;
import static isa.constants.ExaminationConstants.NOW;
import static isa.constants.ExaminationConstants.NOW_MINUS_1;
import static isa.constants.ExaminationConstants.PHARMACIST_ID;
import static isa.constants.ExaminationConstants.PWC_1_END_DATE;
import static isa.constants.ExaminationConstants.PWC_1_START_DATE;
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

import isa.apoteka.domain.Examination;
import isa.apoteka.dto.ExaminationDTO;
import isa.apoteka.repository.ExamintaionRepository;
import isa.apoteka.service.impl.ExaminationServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamintaionServiceTest {

	@Mock
	private ExamintaionRepository examintaionRepositoryMock;

	@InjectMocks
	private ExaminationServiceImpl examinationService;

	@Test
	public void testGetNearestExaminationNotFound() {
		when(examintaionRepositoryMock.findAllByPharmAndStart(PHARMACIST_ID, NOW_MINUS_1)).thenReturn(Arrays.asList());

		Examination examination = examinationService.getNearestExamintaion(PHARMACIST_ID, NOW, true);

		assertNull(examination);
	}

	@Test
	public void testGetNearestExaminationFound() {
		when(examintaionRepositoryMock.findAllByPharmAndStart(PHARMACIST_ID, NOW_MINUS_1))
				.thenReturn(Arrays.asList(EXAMINATION3, EXAMINATION4));

		Examination examination = examinationService.getNearestExamintaion(PHARMACIST_ID, NOW, true);

		assertEquals(3L, examination.getId());

	}

	@Test
	public void testCountAllTermsByDays() {
		when(examintaionRepositoryMock.countTerms(PHARMACIST_ID, PWC_1_START_DATE, PWC_1_END_DATE))
				.thenReturn(EXAMINATION_COUNT);

		List<Long> list = examinationService.countTermsByDays(PHARMACIST_ID, PWC_1_START_DATE, 1);

		assertEquals(1, list.size());
		assertEquals(4L, list.get(0));

	}

	@Test
	public void testMapExaminationToExaminationDTO() {
		ExaminationDTO dto = examinationService.mapExaminationToExaminationDTO(EXAMINATION);

		assertEquals(dto.getId(), EXAMINATION.getId());
		assertEquals(dto.getDuration(), EXAMINATION.getDuration());
		assertEquals(dto.getPatientName(),
				EXAMINATION.getPatient().getFirstName() + " " + EXAMINATION.getPatient().getLastName());
		assertEquals(dto.getPharmacyName(), EXAMINATION.getPharmacistWorkCalendar().getPharmacy().getName());
		assertEquals(dto.getPrice(), EXAMINATION.getPrice());
		assertEquals(dto.getReport(), EXAMINATION.getReport());
		assertEquals(dto.getStartDate(), EXAMINATION.getStartDate());

	}

	@Test
	public void testMapExaminationToExaminationDTONull() {
		ExaminationDTO dto = examinationService.mapExaminationToExaminationDTO(null);

		assertNull(dto);

	}

}
