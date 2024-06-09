package com.marcos.api_pedidos.mapper;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public static List<UserResponseDto> toListDto(List<Users> usuarios) {
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
