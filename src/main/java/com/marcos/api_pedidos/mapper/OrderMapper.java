package com.marcos.api_pedidos.mapper;

import org.modelmapper.ModelMapper;

import com.marcos.api_pedidos.dto.OrderCreateDto;
import com.marcos.api_pedidos.dto.OrderResponseDto;
import com.marcos.api_pedidos.entities.Order;

public class OrderMapper {

	public static Order toOrder(OrderCreateDto dto) {
		return new ModelMapper().map(dto, Order.class);
	}
	
	public static OrderResponseDto toDto(Order cat) {
		return new ModelMapper().map(cat, OrderResponseDto.class);
	}
}
