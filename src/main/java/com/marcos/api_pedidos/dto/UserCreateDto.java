package com.marcos.api_pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserCreateDto {

	@NotBlank
	private String name;
	@NotBlank
	private String email;
	@NotBlank
	private String telefone;
	@NotBlank
	@Size(min = 8, max = 100)
	private String password;

	public UserCreateDto() {
		// TODO Auto-generated constructor stub
	}

	public UserCreateDto(@NotBlank String name, @NotBlank String email, @NotBlank String telefone,
			@NotBlank @Size(min = 8, max = 100) String password) {
		super();
		this.name = name;
		this.email = email;
		this.telefone = telefone;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
