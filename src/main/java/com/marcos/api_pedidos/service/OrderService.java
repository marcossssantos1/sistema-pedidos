package com.marcos.api_pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.marcos.api_pedidos.entities.Order;
import com.marcos.api_pedidos.exceptions.EntityExistingException;
import com.marcos.api_pedidos.exceptions.EntityNotFound;
import com.marcos.api_pedidos.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public Order findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
	}

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order create(Order cat) {
		try {
			return repository.save(cat);
		} catch (DataIntegrityViolationException e) {
			throw new EntityExistingException("Esse nome já existe na base de dados, por favor inserir um nome válido");
		}
	}

	public Order update(Long id, Order order) {
		Order cat = repository.findById(id).orElseThrow(() -> new EntityNotFound("Id inexistente na base dados"));
		cat.setMoment(order.getMoment());
		cat.setOrderStatus(order.getOrderStatus());
		return repository.save(cat);
	}

	public Order deleteById(Long id) {
		if (id != null) {
			repository.deleteById(id);
		} else {
			throw new EntityNotFound("Id inexistente na base dados");
		}

		return null;
	}
}
