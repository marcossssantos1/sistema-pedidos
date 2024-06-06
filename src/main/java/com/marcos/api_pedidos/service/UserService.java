package com.marcos.api_pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcos.api_pedidos.entities.Users;
import com.marcos.api_pedidos.exceptions.EntityNotFound;
import com.marcos.api_pedidos.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
    private  PasswordEncoder passwordEncoder;

	public Users findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
	}

	public List<Users> findAll() {
		return repository.findAll();
	}

	public Users create(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	public Users update(Long id, Users users) {
		Users user = repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
		user.setName(users.getName());
		user.setEmail(users.getEmail());
		user.setTelefone(users.getTelefone());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
    public Users buscarPorUsername(String name) {
        return repository.findByName(name).orElseThrow(
                () -> new EntityNotFound(String.format("Usuario com '%s' não encontrado", name))
        );
    }

    public Users.Role buscarRolePorUsername(String username) {
        return repository.findRoleByName(username);
    }

}
