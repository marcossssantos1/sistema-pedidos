package com.marcos.api_pedidos.mapper;

import org.modelmapper.ModelMapper;

import com.marcos.api_pedidos.dto.ProductCreateDto;
import com.marcos.api_pedidos.dto.ProductResponseDto;
import com.marcos.api_pedidos.entities.Product;

public class ProductMapper {
	
	
	public static Product toProduct(ProductCreateDto dto) {
		return new ModelMapper().map(dto, Product.class);
	}
	
	public static ProductResponseDto toDto(Product prod) {
		return new ModelMapper().map(prod, ProductResponseDto.class);
	}

}
