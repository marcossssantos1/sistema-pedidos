package com.marcos.api_pedidos.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDto {

	@NotBlank
	private String username;
	@NotBlank
	private String password;

	public UserLoginDto() {
	}

	public UserLoginDto(@NotBlank String username, @NotBlank String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserLoginDto [username=" + username + ", password=" + password + "]";
	}

}
