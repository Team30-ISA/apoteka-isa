package isa.apoteka;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
	@Transactional
	public void t1() throws Exception {
		JwtAuthenticationRequest request = new JwtAuthenticationRequest();
		request.setEmail("apoteka.isa2021+ale@gmail.com");
		request.setPassword("adminadmin");

		String input = JSON.stringify(request);
		String uri = "/auth/login";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(input)).andReturn();

		UserTokenState userTokenState = JSON.parse(result.getResponse().getContentAsString(), UserTokenState.class);
		String input2 = JSON.stringify(1L);
		String uri2 = "/api/counseling/getById";

		mockMvc.perform(
				MockMvcRequestBuilders.get(uri2).header("Authorization", "Bearer " + userTokenState.getAccessToken())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).param("id", input2))
				.andExpect(status().is(200));
	}
}
