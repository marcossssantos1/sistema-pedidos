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
import com.marcos.api_pedidos.exceptions.ErrorMessage;
import com.marcos.api_pedidos.mapper.OrderMapper;
import com.marcos.api_pedidos.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@Operation(summary = "Criar um novo pedido", description = "Recurso para realizar um novo pedido", responses = {
			@ApiResponse(responseCode = "201", description = "Pedido criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
			@ApiResponse(responseCode = "422", description = "Dados de entrada estão invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) })
	@PostMapping
	public ResponseEntity<OrderResponseDto> create(@Valid @RequestBody OrderCreateDto dto) {
		Order prod = OrderMapper.toOrder(dto);
		service.create(prod);
		return ResponseEntity.status(HttpStatus.CREATED).body(OrderMapper.toDto(prod));
	}

	@Operation(summary = "Recuperar pedido pelo id", description="Recurso para buscar um pedido pelo id", responses = {
			@ApiResponse(responseCode = "200",
							description = "Pedido recuperado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
			@ApiResponse(responseCode = "404",
					description = "Recurso não encontrado",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> findById(@Valid @PathVariable Long id) {
		Order prod = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.toDto(prod));
	}
	
	
	@Operation(summary = "Recuperar todos os usuarios", description="Recurso para buscar todos os usuario na base de dados", responses = {
			@ApiResponse(responseCode = "200",
							description = "Todos os usuarios foram recuperados com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class)))
	})
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> prod = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(prod);
	}

	@Operation(summary = "Recuperar pedido pelo id e excluir da base", description="Recurso para buscar um pedido pelo id e excluir", responses = {
			@ApiResponse(responseCode = "204",
							description = "Pedido excluido com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404",
					description = "Pedido não encontrado",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@Operation(summary = "Atualizar o pedido", description="Recurso para buscar um pedido pelo id e atualizar os dados", responses = {
			@ApiResponse(responseCode = "204",
							description = "Pedido atualizado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDto.class))),
			@ApiResponse(responseCode = "404",
			description = "Pedido não encontrado",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@PutMapping("/{id}")
	public ResponseEntity<OrderResponseDto> update(@PathVariable Long id, @RequestBody OrderCreateDto dto) {
		Order prod = OrderMapper.toOrder(dto);
		prod = service.update(id, prod);
		prod = service.findById(prod.getId());
		return ResponseEntity.status(HttpStatus.OK).body(OrderMapper.toDto(prod));
	}
}
