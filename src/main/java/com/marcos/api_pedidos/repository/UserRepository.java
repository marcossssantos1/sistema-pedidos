package com.marcos.api_pedidos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marcos.api_pedidos.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByName(String username);

    @Query("select u.role from Users u where u.name like :name")
    Users.Role findRoleByName(String name);
	
}
