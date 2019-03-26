package br.com.paullus.faireconsumer.services;

import java.util.List;

import br.com.paullus.faireconsumer.dtos.BestBuyerOutputDTO;
import br.com.paullus.faireconsumer.dtos.BestSellerOutputDTO;
import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;
import br.com.paullus.faireconsumer.dtos.WorstSellerOutputDTO;

public interface IOrderService {
	List<OrderOutputDTO> list();
	void process(OrderOutputDTO order);
	BestSellerOutputDTO getBestSeller();
	BestSellerOutputDTO getMostRequiredOutOfStock();
	OrderOutputDTO getLargestOrder();
	BestBuyerOutputDTO getTopBuyer();
	WorstSellerOutputDTO getNotSoldWithHighestStock();
}
