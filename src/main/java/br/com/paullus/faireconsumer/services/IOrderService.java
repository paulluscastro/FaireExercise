package br.com.paullus.faireconsumer.services;

import java.util.List;

import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;

public interface IOrderService {
	List<OrderOutputDTO> list();
	void process(OrderOutputDTO order);
}
