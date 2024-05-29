package com.marcos.api_pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.api_pedidos.entities.User;
import com.marcos.api_pedidos.exceptions.EntityNotFound;
import com.marcos.api_pedidos.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User create(User product) {
		return repository.save(product);
	}

	public User update(Long id, User users) {
		User user = repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
		user.setName(users.getName());
		user.setEmail(users.getEmail());
		user.setTelefone(users.getTelefone());
		user.setPassword(users.getPassword());
		return repository.save(user);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
