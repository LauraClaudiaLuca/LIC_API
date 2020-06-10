package com.feedback.feedback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.feedback.dto.LoginDto;
import com.feedback.feedback.dto.RegisterDto;
import com.feedback.feedback.facade.UserFacade;
import com.feedback.feedback.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class UserRestTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserFacade facade;

	private static final String OK_TOKEN="\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwidXNlcm5hbWUiOiJ0ZXN0Iiwicm9sZSI6InRlc3QifQ.AutV3jDCCwnfs7zET3wpZo6yt9Jy8EKS11WR5FNNJO0\"";
	private static final String INVALID_CREDENTIALS_MESSAGE="Invalid credentials";
	@Test
	void loginSuccess() throws Exception {
		LoginDto dto = new LoginDto("test","test");
		when(facade.login(dto)).thenReturn(new User("test","test","test","test"));
		String json = new ObjectMapper().writeValueAsString(dto);
		MvcResult res = mvc.perform(
				post("/login")
				.contentType(APPLICATION_JSON_VALUE)
				.content(json))
				.andReturn();
		assert(res.getResponse().getContentAsString().equals(OK_TOKEN));
		assert(res.getResponse().getStatus() == 200);
	}

	@Test
	void loginFail()throws Exception{
		LoginDto dto = new LoginDto("test","test");
		when(facade.login(dto)).thenReturn(null);
		String json = new ObjectMapper().writeValueAsString(dto);
		MvcResult res = mvc.perform(
				post("/login")
						.contentType(APPLICATION_JSON_VALUE)
						.content(json))
				.andReturn();
		assert(res.getResponse().getContentAsString().equals(INVALID_CREDENTIALS_MESSAGE));
		assert(res.getResponse().getStatus() == 401);
	}
	@Test
	void registerSuccess() throws Exception {
		RegisterDto dto = new RegisterDto("test","test","test@test.com");
		when(facade.register(dto)).thenReturn(true);
		String json = new ObjectMapper().writeValueAsString(dto);
		MvcResult res = mvc.perform(
				post("/register")
						.contentType(APPLICATION_JSON_VALUE)
						.content(json))
				.andReturn();
		assert(res.getResponse().getStatus() == 200);
	}

	@Test
	void registerFail()throws Exception{
		RegisterDto dto = new RegisterDto("test","test","test@test.com");
		when(facade.register(dto)).thenReturn(false);
		String json = new ObjectMapper().writeValueAsString(dto);
		MvcResult res = mvc.perform(
				post("/register")
						.contentType(APPLICATION_JSON_VALUE)
						.content(json))
				.andReturn();
		assert(res.getResponse().getStatus() == 412);
	}

}
