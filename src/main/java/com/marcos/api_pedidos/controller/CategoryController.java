package com.marcos.api_pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.api_pedidos.entities.Category;
import com.marcos.api_pedidos.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category cat = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(cat);
	}

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> cat = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(cat);
	}

	@PostMapping
	public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
		Category cat = service.create(category);
		return ResponseEntity.status(HttpStatus.CREATED).body(cat);
	}

	@PutMapping("{id}")
	public ResponseEntity<Category> update(@PathVariable @Valid Long id, @RequestBody @Valid Category category) {
		Category cat = service.update(id, category);
		cat = service.findById(cat.getId());
		return ResponseEntity.status(HttpStatus.OK).body(cat);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}