package br.com.supera.gamestore.test.utils;

import br.com.supera.gamestore.entities.Order;
import br.com.supera.gamestore.enums.OrderStatus;
import br.com.supera.gamestore.enums.Payment;

public class OrderCreator {
	
	public static Order createOrder() {
		Order order = new Order();		
		order.setSubTotal("200");
		order.setShipping("20");
		order.setTotal("220");
		order.setOrderStatus(OrderStatus.WAITING_PAYMENT);
		order.setPayment(Payment.MONEY);
		order.setClient(UserCreator.createUser());
		return order;
	}
}
