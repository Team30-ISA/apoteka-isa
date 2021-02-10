package isa.apoteka;

import static isa.constants.CounselingConstants.*;
import static isa.constants.CounselingConstants.PATIENT;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import isa.apoteka.domain.Counseling;
import isa.apoteka.service.CounselingService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CounselingIntegrationTest {
	 
	@Autowired
	private CounselingService counselingService;
	
	@Test
	public void Test() {
		Counseling c = counselingService.findOne(1L);
		assertEquals(5, c.getDuration());
	}
	
	/*@Test
	public void TestCreateCounselingFail() {
		Counseling counseling = counselingService.save(COUNSELING0);
		Boolean b1 = counselingService.update(PATIENT, counseling.getId());
		Boolean b2 = counselingService.update(PATIENT, counseling.getId());
		assertTrue(b1);
		assertFalse(b2);
	}*/
}
