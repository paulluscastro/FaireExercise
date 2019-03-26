package br.com.paullus.faireconsumer.repositories;

import java.util.List;

import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;

public interface IOrderRepository {
	List<OrderOutputDTO> list();
	OrderOutputDTO createBackorder(OrderOutputDTO order);
}
