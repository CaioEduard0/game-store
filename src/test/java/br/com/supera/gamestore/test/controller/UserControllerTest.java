package br.com.supera.gamestore.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.supera.gamestore.controllers.UserController;
import br.com.supera.gamestore.services.UserService;
import br.com.supera.gamestore.test.utils.UserCreator;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private UserService userService;
	
	@Test
	void loginPage_ReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(get("/login").contentType("text/html charset=utf-8"))
		.andExpect(status().isOk());
	}
	
	@Test
	void signUpPage_ReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(get("/sign-up").contentType("text/html charset=utf-8"))
		.andExpect(status().isOk());
	}
	
	@Test
	void createAccount_SavesNewUserAndReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(post("/sign-up").contentType("text/html charset=utf-8")
		.content(objectMapper.writeValueAsString(UserCreator.createUser()))
		).andExpect(status().isOk());
	}
}
