package br.com.supera.gamestore.test.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.supera.gamestore.controllers.ProductController;
import br.com.supera.gamestore.services.ProductService;
import br.com.supera.gamestore.services.UserService;
import br.com.supera.gamestore.test.utils.ProductCreator;
import br.com.supera.gamestore.test.utils.UserCreator;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ProductService productService;
	
	@MockBean
	private UserService userService;
	
	@BeforeEach
	void setUp() {		
		Authentication authentication = mock(Authentication.class);
		when(authentication.getPrincipal()).thenReturn(UserCreator.createUser());
		
		SecurityContext securityContext = mock(SecurityContext.class);
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	}
	
	@WithMockUser(username = "user@gmail.com", password = "user123",  roles = "USER")
	@Test
	void productsPage_ReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(get("/").contentType("text/html charset=utf-8"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(username = "user@gmail.com", password = "user123",  roles = "USER")
	@Test
	void productsOrderedByPricePage_ReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(get("/products/order-by-price").contentType("text/html charset=utf-8"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(username = "user@gmail.com", password = "user123",  roles = "USER")
	@Test
	void productsOrderedByScorePage_ReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(get("/products/order-by-score").contentType("text/html charset=utf-8"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(username = "user@gmail.com", password = "user123",  roles = "USER")
	@Test
	void productsOrderedByNamePage_ReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(get("/products/order-by-name").contentType("text/html charset=utf-8"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(username = "admin@gmail.com", password = "admin123",  roles = "ADMIN")
	@Test
	void registerProductPage_ReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(get("/admin/products/register-product").contentType("text/html charset=utf-8"))
		.andExpect(status().isOk());
	}
	
	@WithMockUser(username = "user@gmail.com", password = "user123",  roles = "USER")
	@Test
	void registerProductPage_ReturnsStatus403_WhenUserDoNotHaveAdminRole() throws Exception {
		mockMvc.perform(get("/admin/products/register-product").contentType("text/html charset=utf-8"))
		.andExpect(status().isForbidden());
	}
	
	@WithMockUser(username = "admin@gmail.com", password = "admin123",  roles = "ADMIN")
	@Test
	void registerProduct_SavesNewProductAndReturnsStatus200_WhenSuccessful() throws Exception {
		mockMvc.perform(post("/admin/products/register-product").contentType("text/html charset=utf-8")
		.content(objectMapper.writeValueAsString(ProductCreator.createProduct()))
		).andExpect(status().isOk());
	}
	
	@WithMockUser(username = "user@gmail.com", password = "user123",  roles = "USER")
	@Test
	void registerProduct_ReturnsStatus403_WhenUserDoNotHaveAdminRole() throws Exception {
		mockMvc.perform(post("/admin/products/register-product").contentType("text/html charset=utf-8")
		.content(objectMapper.writeValueAsString(ProductCreator.createProduct()))
		).andExpect(status().isForbidden());
	}
}
