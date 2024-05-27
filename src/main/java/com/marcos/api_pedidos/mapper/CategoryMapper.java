package com.marcos.api_pedidos.mapper;

import org.modelmapper.ModelMapper;

import com.marcos.api_pedidos.dto.CategoryCreateDto;
import com.marcos.api_pedidos.dto.CategoryResponseDto;
import com.marcos.api_pedidos.entities.Category;

public class CategoryMapper {

	public static Category toCategory(CategoryCreateDto dto) {
		return new ModelMapper().map(dto, Category.class);
	}
	
	public static CategoryResponseDto toDto(Category cat) {
		return new ModelMapper().map(cat, CategoryResponseDto.class);
	}
}
