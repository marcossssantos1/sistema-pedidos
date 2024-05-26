package com.marcos.api_pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.api_pedidos.entities.Category;
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

	public Category create(Category category) {
		return repository.save(category);
	}

	public Category update(Long id, Category category) {
		Category cat = repository.findById(id).orElseThrow(() -> new RuntimeException("Id inexistente na base dados"));
		cat.setName(category.getName());
		return repository.save(cat);
	}

	public Category deleteById(Long id) {
		if (id != null) {
			repository.deleteById(id);
		} else {
			throw new RuntimeException();
		}

		return null;
	}
}
