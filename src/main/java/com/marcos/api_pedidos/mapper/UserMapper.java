package com.marcos.api_pedidos.mapper;

import org.modelmapper.ModelMapper;

import com.marcos.api_pedidos.dto.UserCreateDto;
import com.marcos.api_pedidos.dto.UserResponseDto;
import com.marcos.api_pedidos.entities.Users;

public class UserMapper {

	public static Users toUser(UserCreateDto dto) {
		return new ModelMapper().map(dto, Users.class);
	}
	
	public static UserResponseDto toDto(Users prod) {
		return new ModelMapper().map(prod, UserResponseDto.class);
	}
	
}
