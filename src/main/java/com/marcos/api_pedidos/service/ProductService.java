package com.marcos.api_pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.api_pedidos.entities.Product;
import com.marcos.api_pedidos.exceptions.EntityNotFound;
import com.marcos.api_pedidos.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public Product findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public Product create(Product product) {
		return repository.save(product);
	}

	public Product update(Long id, Product product) {
		Product prod = repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
		prod.setName(product.getName());
		prod.setDescription(product.getDescription());
		prod.setPrice(product.getPrice());
		return repository.save(prod);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
