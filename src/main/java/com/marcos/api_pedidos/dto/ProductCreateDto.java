package com.marcos.api_pedidos.dto;

import com.marcos.api_pedidos.entities.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductCreateDto {

	@NotBlank
	@Size(min = 5, max = 1000)
	private String name;
	@NotBlank
	@Size(min = 5, max = 1000)
	private String description;
	private double price;
	private Category category;

	public ProductCreateDto() {
	}

	public ProductCreateDto(@NotBlank @Size(min = 5, max = 1000) String name,
			@NotBlank @Size(min = 5, max = 1000) String description, double price, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public ProductCreateDto(@NotBlank @Size(min = 5, max = 1000) String name,
			@NotBlank @Size(min = 5, max = 1000) String description, double price) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
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
