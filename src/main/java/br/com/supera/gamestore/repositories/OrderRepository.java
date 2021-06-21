package br.com.supera.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.gamestore.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
