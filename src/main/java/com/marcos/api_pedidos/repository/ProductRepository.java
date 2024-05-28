package com.marcos.api_pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.api_pedidos.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
