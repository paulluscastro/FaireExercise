package br.com.paullus.faireconsumer.services;

import br.com.paullus.faireconsumer.dtos.OrderItemOutputDTO;

public interface IOrderItemService {
	void markBackordered(OrderItemOutputDTO item);
}
