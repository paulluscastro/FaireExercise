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
import br.com.paullus.faireconsumer.dtos.ProductOptionOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductsSearchOutputDTO;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Service
public class ProductService implements IProductService {
	@Autowired
	private IFaireConnection connection;

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	private List<ProductOutputDTO> products = new ArrayList<>();

    private ProductsSearchOutputDTO searchProducts(int page) {
        ResponseEntity<ProductsSearchOutputDTO> response = connection
        		.getRestTemplate()
        		.exchange("https://www.faire-stage.com/api/v1/products?page=" + page,
        				HttpMethod.GET,
        				connection.getHttpEntity(),
        				new ParameterizedTypeReference<ProductsSearchOutputDTO>(){});
        return response.getBody();
    }
    private List<ProductOutputDTO> retrieveAllOrders() {
        List<ProductOutputDTO> productoOutputDTOs = new ArrayList<>();
        int page = 1;
        ProductsSearchOutputDTO searchResult = searchProducts(page++);
        while (searchResult.getProducts().size() > 0) {
        	productoOutputDTOs.addAll(searchResult.getProducts());
        	searchResult = searchProducts(page++);
        }
        return productoOutputDTOs;
    }
    @Override
	public List<ProductOutputDTO> list() {
        if (products.size() == 0)
        	products.addAll(retrieveAllOrders());
        return Collections.unmodifiableList(products);
	}
	@Override
	public ProductOutputDTO get(String id) {
		ProductOutputDTO product = list().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (product == null)
        	logger.info("Product not found...");
		return product; 
	}
	@Override
	public List<ProductOutputDTO> listByBrand(String brandId) {
		List<ProductOutputDTO> branded = list().stream().filter(p -> p.getBrand_id().equals(brandId)).collect(Collectors.toList());
		return Collections.unmodifiableList(branded); 
	}
	@Override
	public ProductOptionOutputDTO findOption(String productOptionId) {
		ProductOutputDTO product = list().stream().filter(p -> p.getOptions().stream().filter(po -> po.getId().equals(productOptionId)).count() > 0).findFirst().orElse(null);
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
	
}
