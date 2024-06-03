package com.marcos.api_pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.api_pedidos.entities.OrderItem;
import com.marcos.api_pedidos.pk.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {

}
