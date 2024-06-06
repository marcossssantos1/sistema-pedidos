package com.marcos.api_pedidos.dto;

public class UserResponseDto {

	private String name;
	private String email;
	private String telefone;
	private String password;
	private String role;

	public UserResponseDto() {
	}

	public UserResponseDto(String name, String email, String telefone, String password, String role) {
		super();
		this.name = name;
		this.email = email;
		this.telefone = telefone;
		this.password = password;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
