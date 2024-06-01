package com.marcos.api_pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.api_pedidos.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	

}
