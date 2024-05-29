package com.marcos.api_pedidos.mapper;

import org.modelmapper.ModelMapper;

import com.marcos.api_pedidos.dto.UserCreateDto;
import com.marcos.api_pedidos.dto.UserResponseDto;
import com.marcos.api_pedidos.entities.User;

public class UserMapper {

	public static User toUser(UserCreateDto dto) {
		return new ModelMapper().map(dto, User.class);
	}
	
	public static UserResponseDto toDto(User prod) {
		return new ModelMapper().map(prod, UserResponseDto.class);
	}
	
}
