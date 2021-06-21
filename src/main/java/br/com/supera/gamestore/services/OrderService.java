package br.com.supera.gamestore.services;

import org.springframework.stereotype.Service;

import br.com.supera.gamestore.entities.Order;
import br.com.supera.gamestore.entities.User;
import br.com.supera.gamestore.repositories.OrderRepository;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public void insertOrder(Order order, User user) {
		order.setClient(user);
		orderRepository.save(order);
	}
}
