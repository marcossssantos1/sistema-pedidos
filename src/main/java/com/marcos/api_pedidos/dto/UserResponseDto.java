package com.marcos.api_pedidos.dto;

public class UserResponseDto {

	private String name;
	private String email;
	private String telefone;

	private String password;

	public UserResponseDto() {
		// TODO Auto-generated constructor stub
	}

	public UserResponseDto(String name, String email, String telefone, String password) {
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
