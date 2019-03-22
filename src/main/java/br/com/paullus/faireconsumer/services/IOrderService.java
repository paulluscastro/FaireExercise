package br.com.paullus.faireconsumer.services;

import java.util.List;

import br.com.paullus.faireconsumer.entities.Order;

public interface IOrderService {
	List<Order> list();
}
