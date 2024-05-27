package com.marcos.api_pedidos.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryResponseDto {

	@NotBlank
	private String name;

	public CategoryResponseDto() {
		// TODO Auto-generated constructor stub
	}

	public CategoryResponseDto(@NotBlank String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
