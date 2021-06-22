package br.com.supera.gamestore.test.reposiories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.supera.gamestore.entities.Order;
import br.com.supera.gamestore.repositories.OrderRepository;
import br.com.supera.gamestore.test.utils.OrderCreator;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	void save_SaveAndReturnsOrder_WhenSuccessful() {
		Order order = orderRepository.save(OrderCreator.createOrder());
		
		assertThat(order).isNotNull().isInstanceOf(Order.class);
	}
}
