package br.com.paullus.faireconsumer.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import br.com.paullus.faireconsumer.connection.IFaireConnection;
import br.com.paullus.faireconsumer.dtos.BackorderItemInputDTO;
import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;
import br.com.paullus.faireconsumer.dtos.OrdersSearchOutputDTO;

@Repository
public class OrderRepository implements IOrderRepository {
	@Autowired
	private IFaireConnection connection;


    private OrdersSearchOutputDTO searchOrders(int page) {
        ResponseEntity<OrdersSearchOutputDTO> response = connection
        		.getRestTemplate()
        		.exchange("https://www.faire-stage.com/api/v1/orders?page=" + page,
        				HttpMethod.GET,
        				connection.getHttpEntity(),
        				new ParameterizedTypeReference<OrdersSearchOutputDTO>(){});
        return response.getBody();
    }
    private List<OrderOutputDTO> retrieveAllOrders() {
        List<OrderOutputDTO> orderOutputDTOs = new ArrayList<>();
        int page = 1;
        OrdersSearchOutputDTO searchResult = searchOrders(page++);
        while (searchResult.getOrders().size() > 0) {
        	orderOutputDTOs.addAll(searchResult.getOrders());
        	searchResult = searchOrders(page++);
        }
        return orderOutputDTOs;
    }
	@Override
	public List<OrderOutputDTO> list() {
        return Collections.unmodifiableList(retrieveAllOrders());
	}
	@Override
	public OrderOutputDTO createBackorder(OrderOutputDTO order) {
		final String url = "https://www.faire-stage.com/api/v1/orders/" + order.getId() + "/items/availability";
		List<BackorderItemInputDTO> back = new ArrayList<>();
		order.getItems().stream().filter(oi -> oi.isBackorderered()).forEach(oi -> {
			back.add(new BackorderItemInputDTO(oi, oi.getAvailableQuantity()));
		});
		HttpEntity<List<BackorderItemInputDTO>> request = new HttpEntity<List<BackorderItemInputDTO>>(back, connection.getHeaders());
		OrderOutputDTO backorder = connection.getRestTemplate().postForObject(url, request, OrderOutputDTO.class);
		return backorder;
	}

}
