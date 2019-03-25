package br.com.paullus.faireconsumer.services;

import java.util.List;

import br.com.paullus.faireconsumer.dtos.BestBuyerOutputDTO;
import br.com.paullus.faireconsumer.dtos.BestSellerOutputDTO;
import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;

public interface IOrderService {
	List<OrderOutputDTO> list();
	void process(OrderOutputDTO order);
	BestSellerOutputDTO getBestSeller();
	OrderOutputDTO getLargestOrder();
	BestBuyerOutputDTO getTopBuyer();
}
