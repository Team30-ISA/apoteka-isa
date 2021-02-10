package isa.apoteka;

import static isa.constants.CounselingConstants.COUNSELING_DURATION;
import static isa.constants.CounselingConstants.COUNSELING_ID;
import static isa.constants.CounselingConstants.COUNSELING_PRICE;
import static isa.constants.CounselingConstants.NOW;
import static isa.constants.CounselingConstants.REPORT;
import static isa.constants.GeneralConstants.DERM_EMAIL;
import static isa.constants.GeneralConstants.DERM_PASSWORD;
import static isa.constants.GeneralConstants.LOGIN;
import static isa.constants.GeneralConstants.PHARM_EMAIL;
import static isa.constants.GeneralConstants.PHARM_PASSWORD;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import isa.apoteka.auth.JwtAuthenticationRequest;
import isa.apoteka.domain.UserTokenState;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {
	private static final String URL_PREFIX = "/api/counseling";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetCounselingById() throws Exception {
		MvcResult result = login(DERM_EMAIL, DERM_PASSWORD);

		UserTokenState userTokenState = JSON.parse(result.getResponse().getContentAsString(), UserTokenState.class);

		String id = JSON.stringify(COUNSELING_ID);

		mockMvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "/getById")
				.header("Authorization", "Bearer " + userTokenState.getAccessToken()).contentType(contentType)
				.param("id", id)).andExpect(status().is(200)).andExpect(jsonPath("$.id").value(COUNSELING_ID))
				.andExpect(jsonPath("$.duration").value(COUNSELING_DURATION))
				.andExpect(jsonPath("$.price").value(COUNSELING_PRICE));
	}

	@Test(expected = NestedServletException.class)
	public void testGetCounselingByIdFailed() throws Exception {
		MvcResult result = login(PHARM_EMAIL, PHARM_PASSWORD);

		UserTokenState userTokenState = JSON.parse(result.getResponse().getContentAsString(), UserTokenState.class);

		String id = JSON.stringify(COUNSELING_ID);

		mockMvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "/getById")
				.header("Authorization", "Bearer " + userTokenState.getAccessToken()).contentType(contentType)
				.param("id", id)).andExpect(status().is(401));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSetReport() throws Exception {
		MvcResult result = login(DERM_EMAIL, DERM_PASSWORD);

		UserTokenState userTokenState = JSON.parse(result.getResponse().getContentAsString(), UserTokenState.class);

		Map<String, Object> params = new HashMap<>();
		params.put("counselingId", COUNSELING_ID);
		params.put("report", REPORT);
		String p = JSON.stringify(params);

		mockMvc.perform(MockMvcRequestBuilders.post(URL_PREFIX + "/setReport")
				.header("Authorization", "Bearer " + userTokenState.getAccessToken()).contentType(contentType)
				.content(p)).andExpect(status().is(200));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSetReportBadRequest() throws Exception {
		MvcResult result = login(DERM_EMAIL, DERM_PASSWORD);

		UserTokenState userTokenState = JSON.parse(result.getResponse().getContentAsString(), UserTokenState.class);

		Map<String, Object> params = new HashMap<>();
		params.put("counselingId", COUNSELING_ID);
		params.put("report", "");
		String p = JSON.stringify(params);

		mockMvc.perform(MockMvcRequestBuilders.post(URL_PREFIX + "/setReport")
				.header("Authorization", "Bearer " + userTokenState.getAccessToken()).contentType(contentType)
				.content(p)).andExpect(status().is(400));
	}

	@Test
	public void testGetNearestCounseling() throws Exception {
		MvcResult result = login(DERM_EMAIL, DERM_PASSWORD);

		UserTokenState userTokenState = JSON.parse(result.getResponse().getContentAsString(), UserTokenState.class);

		String start = JSON.stringify(NOW);

		mockMvc.perform(MockMvcRequestBuilders.get(URL_PREFIX + "/getNearestCounseling")
				.header("Authorization", "Bearer " + userTokenState.getAccessToken()).contentType(contentType)
				.param("start", start).param("finished", "true")).andExpect(status().is(200));
	}

	public MvcResult login(String email, String password) throws Exception {
		JwtAuthenticationRequest request = new JwtAuthenticationRequest();
		request.setEmail(email);
		request.setPassword(password);

		String input = JSON.stringify(request);

		MvcResult ret = mockMvc.perform(MockMvcRequestBuilders.post(LOGIN).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(input)).andReturn();
		return ret;
	}
}