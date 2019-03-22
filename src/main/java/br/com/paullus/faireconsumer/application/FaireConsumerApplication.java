/**
 * 
 */
package br.com.paullus.faireconsumer.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.paullus.faireconsumer.entities.Order;
import br.com.paullus.faireconsumer.entities.OrdersSearch;
import br.com.paullus.faireconsumer.entities.Product;
import br.com.paullus.faireconsumer.entities.ProductsSearch;
import br.com.paullus.faireconsumer.enums.OrderState;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@SpringBootApplication
public class FaireConsumerApplication {

    private static final Logger log = LoggerFactory.getLogger(FaireConsumerApplication.class);
    
    private static ObjectMapper getObjectMapper()
    {
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    	return objectMapper;
    }
    private static RestTemplate getRestTemplate() {
    	RestTemplate restTemplate = new RestTemplate();
    	List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    	MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
    	jsonMessageConverter.setObjectMapper(getObjectMapper());
    	messageConverters.add(jsonMessageConverter);
    	restTemplate.setMessageConverters(messageConverters);
    	return restTemplate;
    }
    private static HttpEntity<String> getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-FAIRE-ACCESS-TOKEN", "HQLA9307HSLQYTC24PO2G0LITTIOHS2MJC8120PVZ83HJK4KACRZJL91QB7K01NWS2TUCFXGCHQ8HVED8WNZG0KS6XRNBFRNGY71");
        return new HttpEntity<String>("parameters", headers);
    }
    private static OrdersSearch searchOrders(int page) {
        ResponseEntity<OrdersSearch> response = getRestTemplate().exchange("https://www.faire-stage.com/api/v1/orders?page=" + page, HttpMethod.GET, getHttpEntity(), new ParameterizedTypeReference<OrdersSearch>(){});
        return response.getBody();
    }
    private static ProductsSearch searchProducts(int page) {
        ResponseEntity<ProductsSearch> response = getRestTemplate().exchange("https://www.faire-stage.com/api/v1/products?page=" + page, HttpMethod.GET, getHttpEntity(), new ParameterizedTypeReference<ProductsSearch>(){});
        return response.getBody();
    }
    private static List<Product> allProducts() {
        List<Product> products = new ArrayList<>();
        int page = 1;
        ProductsSearch productsSearchResult = searchProducts(page++); 
        while (productsSearchResult.getProducts().size() > 0) {
        	products.addAll(productsSearchResult.getProducts());
        	productsSearchResult = searchProducts(page++);
        }
        log.info("==================== LISTING ALL " + products.size() + " PRODUCTS ====================");
        products.stream().forEach(o -> log.info(o.toString()));
        return products;
    }
    private static List<Order> allOrders() {
        List<Order> orders = new ArrayList<>();
        int page = 1;
        OrdersSearch ordersSearchResult = searchOrders(page++); 
        while (ordersSearchResult.getOrders().size() > 0) {
        	orders.addAll(ordersSearchResult.getOrders());
        	ordersSearchResult = searchOrders(page++);
        }
        log.info("==================== LISTING ALL " + orders.size() + " ORDERS ====================");
        orders.stream().forEach(o -> log.info(o.toString()));
        return orders;
    }
    public static void main(String args[]) {
    	List<Product> products = allProducts();
    	List<Order> orders = allOrders();
    	List<Order> newOrders = orders.stream().filter(o -> o.getState() == OrderState.NEW).collect(Collectors.toList());
        log.info("==================== LISTING NEW " + newOrders.size() + " ORDERS ====================");
        newOrders.stream().forEach(o -> log.info(o.toString()));
    }
}
