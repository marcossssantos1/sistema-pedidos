package com.marcos.api_pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.api_pedidos.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
