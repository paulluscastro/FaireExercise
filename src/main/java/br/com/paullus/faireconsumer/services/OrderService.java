/**
 * 
 */
package br.com.paullus.faireconsumer.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paullus.faireconsumer.dtos.BestBuyerOutputDTO;
import br.com.paullus.faireconsumer.dtos.BestSellerOutputDTO;
import br.com.paullus.faireconsumer.dtos.OrderItemOutputDTO;
import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductOptionOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;
import br.com.paullus.faireconsumer.dtos.WorstSellerOutputDTO;
import br.com.paullus.faireconsumer.repositories.IOrderRepository;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepository;
	@Autowired
	private IProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	private List<ProductOutputDTO> products = null;
	
	private List<ProductOutputDTO> getAllProducts(){
		if (products == null)
			products = productService.list();
		return products;
	}
	
	private ProductOutputDTO getProduct(String id){
		return getAllProducts().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
	}
	
	public ProductOptionOutputDTO findOption(String productOptionId) {
		ProductOutputDTO product = getAllProducts().stream().filter(p -> p.getOptions().stream().filter(po -> po.getId().equals(productOptionId)).count() > 0).findFirst().orElse(null);
		if (product == null) {
	        logger.info("Product option does not exist. ID: '" + productOptionId + "'");
	        return null;
		}
		ProductOptionOutputDTO option = product.getOptions().stream().filter(po -> po.getId().equals(productOptionId)).findFirst().orElse(null);
		if (option == null) {
	        logger.info("Product option does not exist. ID: '" + productOptionId + "'");
	        return null;
		}
		return option;
	}

    @Override
	public List<OrderOutputDTO> list() {
        return Collections.unmodifiableList(orderRepository.list());
	}
	
	private boolean checkAllProductsExist(OrderOutputDTO order)
	{
		for(OrderItemOutputDTO oi : order.getItems())
		{
			ProductOutputDTO product = getProduct(oi.getProduct_id());
			if (product == null)
			{
		        // logger.info("Product id does not exist '" + oi.getProduct_id() + "'");
		        return false;
			}
			ProductOptionOutputDTO productOption = findOption(oi.getProduct_option_id());
			if (productOption == null)
			{
		        // logger.info("ProductOption id does not exist '" + oi.getProduct_option_id() + "'");
		        return false;
			}
		}
		return true;
	}
	private Map<String, Long> sumEqualItems(OrderOutputDTO order)
	{
		Map<String, Long> total = new HashMap<>(); 
		for(OrderItemOutputDTO oi : order.getItems())
		{
			if (!total.containsKey(oi.getProduct_option_id()))
				total.put(oi.getProduct_option_id(), 0L);
			total.put(oi.getProduct_option_id(), total.get(oi.getProduct_option_id()) + oi.getQuantity()); 
		}
		return total;
	}
	private boolean checkAcceptability(OrderOutputDTO order)
	{
		if (!checkAllProductsExist(order)) return false;
		Map<String, Long> total = sumEqualItems(order);
		for(OrderItemOutputDTO oi : order.getItems())
		{
			ProductOutputDTO product = getProduct(oi.getProduct_id());
			if (!product.isActive()) return false;
			ProductOptionOutputDTO productOption = product.getOptions().stream().filter(po -> po.getId().equals(oi.getProduct_option_id())).findFirst().orElse(null);
			if (!productOption.isActive()) return false;
			if (productOption.getAvailable_quantity() < total.get(oi.getProduct_option_id()))
			{
		        // logger.info("[ORDER ID: '" + order.getId() + "', OI_ID: '" + oi.getId() + "']");
		        // logger.info("Total product options for '" + productOption.getName() + "' not available [Has : " + productOption.getAvailable_quantity() + ", Ordered: " + total.get(oi.getProduct_option_id()) + "]");
		        oi.backorder(productOption.getAvailable_quantity());
		        return false;
			}
		}
		return true;
	}
	private void updateLevels(OrderOutputDTO order)
	{
		// InventoryLevelUpdateInputDTO inventoryUpdate = new InventoryLevelUpdateInputDTO();
		order.getItems().stream().forEach(oi ->{
			ProductOutputDTO product = getProduct(oi.getProduct_id());
			product.getOptions()
				.stream()
				.filter(po -> po.getId().equals(oi.getProduct_option_id()))
				.forEach(po -> {
					po.sell(oi.getQuantity());
					// inventoryUpdate.add(new ProductOptionInventoryLevelUpdateInputDTO(oi.getSku(), oi.getQuantity()));
				});
		});
		// final String url = "https://www.faire.com/api/v1/products/options/inventory-levels";
		// HttpEntity<InventoryLevelUpdateInputDTO> request = new HttpEntity<InventoryLevelUpdateInputDTO>(inventoryUpdate, connection.getHeaders());
		// InventoryLevelUpdateOutputDTO output = connection.getRestTemplate().postForObject(url, request, InventoryLevelUpdateOutputDTO.class);
		// productService.updateLocalInventories();
	}
	@Override
	public void process(OrderOutputDTO order) {
		// Commented due to lack of data to test
		/*
		if (order.getState() != OrderState.NEW) {
	        logger.info("Order is not NEW. ID '" + order.getId() + "'");
	        return;
		}
		*/
		if (!checkAcceptability(order))
		{
			/// createBackorder(order);
			// logger.info("Create backorder for: " + order.getId());
			return;
		}
		updateLevels(order);
	}
	@Override
	public BestSellerOutputDTO getBestSeller() {
		Map<String, Long> totals  = new HashMap<>();
		list()
		.stream()
		// .filter(order.getState() == OrderState.PROCESSING)
		.forEach(o ->{
			o.getItems().stream().forEach(oi ->{
				if (!totals.containsKey(oi.getProduct_option_id()))
					totals.put(oi.getProduct_option_id(), 0L);
				totals.put(oi.getProduct_option_id(), totals.get(oi.getProduct_option_id()) + oi.getQuantity()); 
			});
		});
		Iterator<Entry<String, Long>> iterator = totals.entrySet().iterator();
		Entry<String, Long> max = iterator.next();
		while(iterator.hasNext()) {
			Entry<String, Long> current = iterator.next();
			if (current.getValue() > max.getValue())
				max = current;
		}
		ProductOptionOutputDTO option = findOption(max.getKey());
		ProductOutputDTO product = getProduct(option.getProduct_id());
		return new BestSellerOutputDTO(product, findOption(max.getKey()), max.getValue());
	}
	@Override
	public OrderOutputDTO getLargestOrder() {
		OrderOutputDTO largest = list().stream().max(Comparator.comparing(OrderOutputDTO::getTotalOrder)).get();
		return largest;
	}
	@Override
	public BestBuyerOutputDTO getTopBuyer() {
		Map<String, Long> buyersPerState = new HashMap<>();
		list()
		.stream()
		// .filter(order.getState() == OrderState.PROCESSING)
		.forEach(o ->{
			if (!buyersPerState.containsKey(o.getAddress().getState()))
				buyersPerState.put(o.getAddress().getState(), 0L);
			buyersPerState.put(o.getAddress().getState(), buyersPerState.get(o.getAddress().getState()) + 1);
		});
		Iterator<Entry<String, Long>> iterator = buyersPerState.entrySet().iterator();
		Entry<String, Long> max = iterator.next();
		while(iterator.hasNext()) {
			Entry<String, Long> current = iterator.next();
			if (current.getValue() > max.getValue())
				max = current;
		}
		return new BestBuyerOutputDTO(max.getKey(), max.getValue());
	}
	@Override
	public BestSellerOutputDTO getMostRequiredOutOfStock() {
		Map<String, Long> totals  = new HashMap<>();
		list()
		.stream()
		// .filter(order.getState() == OrderState.PROCESSING)
		.forEach(o ->{
			o.getItems()
				.stream()
				.filter(oi -> oi.isBackorderered())
				.forEach(oi ->{
					if (!totals.containsKey(oi.getProduct_option_id()))
						totals.put(oi.getProduct_option_id(), 0L);
					totals.put(oi.getProduct_option_id(), totals.get(oi.getProduct_option_id()) + oi.getQuantity()); 
				});
		});
		Iterator<Entry<String, Long>> iterator = totals.entrySet().iterator();
		Entry<String, Long> max = iterator.next();
		while(iterator.hasNext()) {
			Entry<String, Long> current = iterator.next();
			if (current.getValue() > max.getValue())
				max = current;
		}
		ProductOptionOutputDTO option = findOption(max.getKey());
		ProductOutputDTO product = getProduct(option.getProduct_id());
		return new BestSellerOutputDTO(product, findOption(max.getKey()), max.getValue());
	}
	@Override
	public WorstSellerOutputDTO getNotSoldWithHighestStock() {
		List<ProductOptionOutputDTO> stock = new ArrayList<>();
		productService.list()
			.stream()
			.forEach(p -> stock.addAll(p.getOptions()));
		list()
			.stream()
			// .filter(order.getState() == OrderState.PROCESSING)
			.forEach(o ->{
				o.getItems().stream().forEach(oi ->{
					ProductOptionOutputDTO option = stock.stream().filter(po -> po.getId().equals(oi.getId())).findFirst().orElse(null);
					stock.remove(option);
			});
		});
		if (stock.size() == 0)
			return new WorstSellerOutputDTO(null, null, 0);
		else
		{
			ProductOptionOutputDTO notSoldWithHighStock = stock.stream().max(Comparator.comparing(ProductOptionOutputDTO::getAvailable_quantity)).get();
			ProductOutputDTO product = getProduct(notSoldWithHighStock.getProduct_id());
			return new WorstSellerOutputDTO(product, notSoldWithHighStock, notSoldWithHighStock.getAvailable_quantity());
		}
	}
}
