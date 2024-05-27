package com.marcos.api_pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryCreateDto {

	@NotBlank
	@Size(min = 5, max = 1000)
	private String name;

	public CategoryCreateDto() {
		// TODO Auto-generated constructor stub
	}

	public CategoryCreateDto(@NotBlank @Size(min = 5, max = 1000) String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
