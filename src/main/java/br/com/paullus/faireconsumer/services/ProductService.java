/**
 * 
 */
package br.com.paullus.faireconsumer.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.paullus.faireconsumer.connection.IFaireConnection;
import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductsSearchOutputDTO;
import br.com.paullus.faireconsumer.entities.Product;
import br.com.paullus.faireconsumer.entities.ProductOption;
import br.com.paullus.faireconsumer.repositories.IGenericRepository;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Service
public class ProductService implements IProductService {
	@Autowired
	private IFaireConnection connection;
	@Autowired
	private IProductOptionService productsOptionService;
	@Autowired
	private IGenericRepository<Product> productsRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductsSearchOutputDTO searchProducts(int page) {
        ResponseEntity<ProductsSearchOutputDTO> response = connection
        		.getRestTemplate()
        		.exchange("https://www.faire-stage.com/api/v1/products?page=" + page,
        				HttpMethod.GET,
        				connection.getHttpEntity(),
        				new ParameterizedTypeReference<ProductsSearchOutputDTO>(){});
        return response.getBody();
    }
    private void retrieveAllOrders() {
        List<ProductOutputDTO> productoOutputDTOs = new ArrayList<>();
        int page = 1;
        ProductsSearchOutputDTO searchResult = searchProducts(page++);
        while (searchResult.getProducts().size() > 0) {
        	productoOutputDTOs.addAll(searchResult.getProducts());
        	searchResult = searchProducts(page++);
        }
        productoOutputDTOs.stream().forEach(p ->{
        	Product product = productsRepository.create(new Product(p));
        	p.getOptions().stream().forEach(po -> productsOptionService.create(new ProductOption(product, po)));
        });
    }
    @Override
	public List<Product> list() {
        logger.info("Listing all products...");
        if (productsRepository.list().size() == 0)
        	retrieveAllOrders();
        return productsRepository.list();
	}
	@Override
	public Product get(String id) {
        logger.info("Find product by id: '" + id + "'");
		Product product = productsRepository.get(id);
        if (product == null)
        	logger.info("Product not found...");
		return product; 
	}
	@Override
	public List<Product> listByBrand(String brandId) {
        logger.info("Listing products of brand " + brandId + "...");
		List<Product> branded = list().stream().filter(p -> p.getBrandId().equals(brandId)).collect(Collectors.toList());
        logger.info("Listed " + branded.size() + " products of brand " + brandId + "...");
		return Collections.unmodifiableList(branded); 
	}
	
}
