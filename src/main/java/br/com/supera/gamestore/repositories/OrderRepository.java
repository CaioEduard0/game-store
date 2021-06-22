package br.com.supera.gamestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.supera.gamestore.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
