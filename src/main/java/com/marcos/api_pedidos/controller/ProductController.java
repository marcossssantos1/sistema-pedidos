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

import com.marcos.api_pedidos.dto.ProductCreateDto;
import com.marcos.api_pedidos.dto.ProductResponseDto;
import com.marcos.api_pedidos.entities.Product;
import com.marcos.api_pedidos.mapper.ProductMapper;
import com.marcos.api_pedidos.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@PostMapping
	public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductCreateDto dto){
	    Product prod = ProductMapper.toProduct(dto);
	    service.create(prod);
	    return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDto(prod));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> findById(@Valid @PathVariable Long id){
		Product prod = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toDto(prod));
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> prod = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(prod);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody ProductCreateDto dto){
		Product prod = ProductMapper.toProduct(dto);
		prod = service.update(id, prod);
		prod = service.findById(prod.getId());
		return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toDto(prod));
	}
}
