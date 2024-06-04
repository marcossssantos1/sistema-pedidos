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
import com.marcos.api_pedidos.exceptions.ErrorMessage;
import com.marcos.api_pedidos.mapper.ProductMapper;
import com.marcos.api_pedidos.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Operation(summary = "Criar um novo produto", description = "Recurso para cirar um novo produto", responses = {
			@ApiResponse(responseCode = "201", description = "Produto criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))),
			@ApiResponse(responseCode = "422", description = "Dados de entrada estão invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) })
	@PostMapping
	public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductCreateDto dto){
	    Product prod = ProductMapper.toProduct(dto);
	    service.create(prod);
	    return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDto(prod));
	}
	
	@Operation(summary = "Recuperar produto pelo id", description="Recurso para buscar um produto pelo id", responses = {
			@ApiResponse(responseCode = "200",
							description = "Produto recuperado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))),
			@ApiResponse(responseCode = "404",
					description = "Recurso não encontrado",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> findById(@Valid @PathVariable Long id){
		Product prod = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toDto(prod));
	}
	
	@Operation(summary = "Recuperar todos os produtos", description="Recurso para buscar todos os produtos na base de dados", responses = {
			@ApiResponse(responseCode = "200",
							description = "Todos os produtos foram recuperados com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class)))
	})
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		List<Product> prod = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(prod);
	}
	
	@Operation(summary = "Recuperar produto pelo id e excluir da base", description="Recurso para buscar um produto pelo id e excluir", responses = {
			@ApiResponse(responseCode = "204",
							description = "Produto excluido com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404",
					description = "Produto não encontrado",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@Operation(summary = "Atualizar um produto", description="Recurso para buscar um produto pelo id e atualizar os dados", responses = {
			@ApiResponse(responseCode = "204",
							description = "Produto atualizado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponseDto.class))),
			@ApiResponse(responseCode = "404",
			description = "Pedido não encontrado",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody ProductCreateDto dto){
		Product prod = ProductMapper.toProduct(dto);
		prod = service.update(id, prod);
		prod = service.findById(prod.getId());
		return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.toDto(prod));
	}
}
