package br.com.supera.gamestore.test.services;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.supera.gamestore.entities.Order;
import br.com.supera.gamestore.repositories.OrderRepository;
import br.com.supera.gamestore.services.OrderService;
import static br.com.supera.gamestore.test.utils.OrderCreator.*;
import static br.com.supera.gamestore.test.utils.UserCreator.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class OrderServiceTest {
	
	@InjectMocks
	private OrderService orderService;
	
	@Mock
	private OrderRepository orderRepositoryMock;
	
	@Test
	void insertProduct_SavesProduct_WhenSuccessful() {
		when(orderRepositoryMock.save(any(Order.class))).thenReturn(createOrder());
		
		assertThatCode(() -> orderService.insertOrder(createOrder(), createUser())).doesNotThrowAnyException();
	}
}
