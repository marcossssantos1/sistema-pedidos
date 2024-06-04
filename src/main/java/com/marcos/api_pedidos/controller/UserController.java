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

import com.marcos.api_pedidos.dto.UserCreateDto;
import com.marcos.api_pedidos.dto.UserResponseDto;
import com.marcos.api_pedidos.entities.User;
import com.marcos.api_pedidos.exceptions.ErrorMessage;
import com.marcos.api_pedidos.mapper.UserMapper;
import com.marcos.api_pedidos.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
	private UserService service;

	@Operation(summary = "Criar um novo usuario", description = "Recurso para cirar um novo usuario", responses = {
			@ApiResponse(responseCode = "201", description = "Usuario criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
			@ApiResponse(responseCode = "422", description = "Dados de entrada estão invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))) })
	@PostMapping
	public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto dto) {
		User prod = UserMapper.toUser(dto);
		service.create(prod);
		return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(prod));
	}

	@Operation(summary = "Recuperar usuario pelo id", description="Recurso para buscar um usuario pelo id", responses = {
			@ApiResponse(responseCode = "200",
							description = "Usuario recuperado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
			@ApiResponse(responseCode = "404",
					description = "Usuario não encontrado",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> findById(@Valid @PathVariable Long id) {
		User prod = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(prod));
	}

	@Operation(summary = "Recuperar todos os usuarios", description="Recurso para buscar todos os usuarios na base de dados", responses = {
			@ApiResponse(responseCode = "200",
							description = "Todos os usuarios foram recuperados com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class)))
	})
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> prod = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(prod);
	}

	@Operation(summary = "Recuperar usuario pelo id e excluir da base", description="Recurso para buscar um usuario pelo id e excluir", responses = {
			@ApiResponse(responseCode = "204",
							description = "Usuario excluido com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404",
					description = "Usuario não encontrado",
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@Operation(summary = "Atualizar um usuario", description="Recurso para buscar um usuario pelo id e atualizar os dados", responses = {
			@ApiResponse(responseCode = "204",
							description = "Usuario atualizado com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
			@ApiResponse(responseCode = "404",
			description = "Usuario não encontrado",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
	})
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserCreateDto dto) {
		User prod = UserMapper.toUser(dto);
		prod = service.update(id, prod);
		prod = service.findById(prod.getId());
		return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toDto(prod));
	}

}
