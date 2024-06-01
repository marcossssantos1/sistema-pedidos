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

import com.marcos.api_pedidos.dto.OrderCreateDto;
import com.marcos.api_pedidos.dto.OrderResponseDto;
import com.marcos.api_pedidos.entities.Order;
import com.marcos.api_pedidos.mapper.OrderMapper;
import com.marcos.api_pedidos.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@PostMapping
	public ResponseEntity<OrderResponseDto> create(@Valid @RequestBody OrderCreateDto dto){
	    Order prod = OrderMapper.toOrder(dto);
	    service.create(prod);
	    return ResponseEntity.status(HttpStatus.CREATED).body(OrderMapper.toDto(prod));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> findById(@Valid @PathVariable Long id){
		Order prod = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.toDto(prod));
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> prod = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(prod);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrderResponseDto> update(@PathVariable Long id, @RequestBody OrderCreateDto dto){
		Order prod = OrderMapper.toOrder(dto);
		prod = service.update(id, prod);
		prod = service.findById(prod.getId());
		return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.toDto(prod));
	}
}
