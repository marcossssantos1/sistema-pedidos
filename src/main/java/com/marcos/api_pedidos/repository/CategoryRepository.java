package com.marcos.api_pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.api_pedidos.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
