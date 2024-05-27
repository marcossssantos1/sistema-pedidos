package com.marcos.api_pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcos.api_pedidos.entities.Category;
import com.marcos.api_pedidos.exceptions.EntityExistingException;
import com.marcos.api_pedidos.exceptions.EntityNotFound;
import com.marcos.api_pedidos.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
	}

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category create(Category cat) {
		try {
			return repository.save(cat);
		} catch (DataIntegrityViolationException e) {
			throw new EntityExistingException("Esse nome já existe na base de dados, por favor inserir um nome válido");
		}
	}

	public Category update(Long id, Category category) {
		Category cat = repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
		cat.setName(category.getName());
		return repository.save(cat);
	}

	public Category deleteById(Long id) {
		if (id != null) {
			repository.deleteById(id);
		} else {
			throw new EntityNotFound("Id inexistente na base dados");
		}

		return null;
	}
}
