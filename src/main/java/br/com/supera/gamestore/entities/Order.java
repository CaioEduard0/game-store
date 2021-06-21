package br.com.supera.gamestore.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.supera.gamestore.enums.OrderStatus;
import br.com.supera.gamestore.enums.Payment;

@Entity
@Table(name = "ORDERS")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private final LocalDateTime orderMoment = LocalDateTime.now();
	private BigDecimal subTotal;
	private BigDecimal shipping;
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@Enumerated(EnumType.STRING)
	private Payment payment;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "client_id")
	private User client;

	public Long getId() {
		return id;
	}
	
	public LocalDateTime getOrderMoment() {
		return orderMoment;
	}
	
	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = new BigDecimal(subTotal);
	}
	
	public BigDecimal getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = new BigDecimal(shipping);
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = new BigDecimal(total);
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}	

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderMoment == null) ? 0 : orderMoment.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((shipping == null) ? 0 : shipping.hashCode());
		result = prime * result + ((subTotal == null) ? 0 : subTotal.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderMoment == null) {
			if (other.orderMoment != null)
				return false;
		} else if (!orderMoment.equals(other.orderMoment))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (payment != other.payment)
			return false;
		if (shipping == null) {
			if (other.shipping != null)
				return false;
		} else if (!shipping.equals(other.shipping))
			return false;
		if (subTotal == null) {
			if (other.subTotal != null)
				return false;
		} else if (!subTotal.equals(other.subTotal))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}
}
