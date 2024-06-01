package com.marcos.api_pedidos.dto;

import java.time.Instant;

import com.marcos.api_pedidos.enums.OrderStatus;

import jakarta.validation.constraints.NotBlank;

public class OrderCreateDto {

	@NotBlank
	private Instant moment;
	private OrderStatus status;

	public OrderCreateDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderCreateDto(@NotBlank Instant moment, OrderStatus status) {
		super();
		this.moment = moment;
		this.status = status;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
