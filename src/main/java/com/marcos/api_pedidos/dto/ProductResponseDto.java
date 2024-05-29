package com.marcos.api_pedidos.dto;

import com.marcos.api_pedidos.entities.Category;

import jakarta.validation.constraints.NotBlank;

public class ProductResponseDto {

	@NotBlank
	private String name;
	@NotBlank
	private String description;
	private double price;
	private Category category;

	public ProductResponseDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductResponseDto(@NotBlank String name, @NotBlank String description, double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public ProductResponseDto(@NotBlank String name, @NotBlank String description, double price, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
