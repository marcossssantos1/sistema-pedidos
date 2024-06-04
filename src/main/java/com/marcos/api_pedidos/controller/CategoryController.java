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

import com.marcos.api_pedidos.dto.CategoryCreateDto;
import com.marcos.api_pedidos.dto.CategoryResponseDto;
import com.marcos.api_pedidos.entities.Category;
import com.marcos.api_pedidos.exceptions.ErrorMessage;
import com.marcos.api_pedidos.mapper.CategoryMapper;
import com.marcos.api_pedidos.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@Operation(summary = "Recuperar categoria pelo id", description="Recurso para buscar uma categoria pelo id", responses = {
			@ApiResponse(responseCode = "200",
							description = "Categoria recuperada com sucesso",
							content = @Content(mediaType = "aplication/json", schema = @Schema(implementation = CategoryResponseDto.class))),
			@ApiResponse(responseCode = "404",
					description = "Categoria não encontrada",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) {
		Category cat = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(CategoryMapper.toDto(cat));
	}

	@Operation(summary = "Recuperar todas as categorias", description="Recurso para buscar todos as categorias na base de dados", responses = {
			@ApiResponse(responseCode = "200",
							description = "Todas as categorias foram recuperadas com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class)))
	})
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> cat = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(cat);
	}

	@Operation(summary = "Criar uma nova categoria", description = "Recurso para cirar uma nova categoria", responses = {
			@ApiResponse(responseCode = "201", description = "Categoria criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))),
			@ApiResponse(responseCode = "422", description = "Dados de entrada estão invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) })
	@PostMapping
	public ResponseEntity<CategoryResponseDto> create(@Valid @RequestBody CategoryCreateDto dto) {
		Category cat = CategoryMapper.toCategory(dto);
		service.create(cat);
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toDto(cat));
	}

	@Operation(summary = "Atualizar uma categoria", description="Recurso para buscar uma categoria pelo id e atualizar os dados", responses = {
			@ApiResponse(responseCode = "204",
							description = "Categoria atualizado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponseDto.class))),
			@ApiResponse(responseCode = "404",
			description = "Categoria não encontrada",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@PutMapping("{id}")
	public ResponseEntity<CategoryResponseDto> update(@PathVariable @Valid Long id,
			@RequestBody @Valid CategoryCreateDto dto) {
		Category cat = CategoryMapper.toCategory(dto);
		cat = service.update(id, cat);
		cat = service.findById(cat.getId());
		return ResponseEntity.status(HttpStatus.OK).body(CategoryMapper.toDto(cat));

	}

	@Operation(summary = "Recuperar categoria pelo id e excluir da base", description="Recurso para buscar um categoria pelo id e excluir", responses = {
			@ApiResponse(responseCode = "204",
							description = "Categoria excluida com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404",
					description = "Categoria não encontrada",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
