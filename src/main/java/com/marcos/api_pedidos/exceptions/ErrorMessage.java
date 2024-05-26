package com.marcos.api_pedidos.exceptions;

import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorMessage {

	private String message;
	private int status;
	private String error;
	private String path;
	private String method;
	
	public ErrorMessage() {
		// TODO Auto-generated constructor stub
	}

	public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
		this.path = request.getRequestURI();
		this.status = status.value();
		this.message = message;
		this.method = request.getMethod();
		this.error = status.getReasonPhrase();
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getPath() {
		return path;
	}

	public String getMethod() {
		return method;
	}
	
	
}
