/**
 * 
 */
package br.com.paullus.faireconsumer.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.paullus.faireconsumer.connection.IFaireConnection;
import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;
import br.com.paullus.faireconsumer.dtos.OrdersSearchOutputDTO;
import br.com.paullus.faireconsumer.entities.Order;
import br.com.paullus.faireconsumer.entities.OrderItem;
import br.com.paullus.faireconsumer.entities.Product;
import br.com.paullus.faireconsumer.entities.ProductOption;
import br.com.paullus.faireconsumer.repositories.IGenericRepository;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Service
public class OrderService implements IOrderService {

	@Autowired
	private IFaireConnection connection;
	@Autowired
	private IGenericRepository<Order> ordersRepository;
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductOptionService productOptionService;
	
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private OrdersSearchOutputDTO searchOrders(int page) {
        ResponseEntity<OrdersSearchOutputDTO> response = connection
        		.getRestTemplate()
        		.exchange("https://www.faire-stage.com/api/v1/orders?page=" + page,
        				HttpMethod.GET,
        				connection.getHttpEntity(),
        				new ParameterizedTypeReference<OrdersSearchOutputDTO>(){});
        return response.getBody();
    }
    private void retrieveAllOrders() {
        List<OrderOutputDTO> orderOutputDTOs = new ArrayList<>();
        int page = 1;
        OrdersSearchOutputDTO searchResult = searchOrders(page++);
        while (searchResult.getOrders().size() > 0) {
        	orderOutputDTOs.addAll(searchResult.getOrders());
        	searchResult = searchOrders(page++);
        }
        orderOutputDTOs.stream().forEach(o ->{
        	Order order = ordersRepository.create(new Order(o));
        	o.getItems().forEach(oi -> {
            	Product product = productService.get(oi.getProduct_id());
            	ProductOption productOption = productOptionService.get(oi.getProduct_option_id());
            	order.addItem(new OrderItem(order, product, productOption, oi));
        	});
        });
    }
	@Override
	public List<Order> list() {
        logger.info("Listing all orders...");
        if (ordersRepository.list().size() == 0)
        	retrieveAllOrders();
        return ordersRepository.list();
	}
}
