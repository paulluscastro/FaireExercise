/**
 * 
 */
package br.com.paullus.faireconsumer.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import br.com.paullus.faireconsumer.connection.IFaireConnection;
import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;
import br.com.paullus.faireconsumer.dtos.ProductsSearchOutputDTO;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Repository
public class ProductRepository implements IProductRepository {
	@Autowired
	private IFaireConnection connection;
	
    private ProductsSearchOutputDTO searchProducts(int page) {
        ResponseEntity<ProductsSearchOutputDTO> response = connection
        		.getRestTemplate()
        		.exchange("https://www.faire-stage.com/api/v1/products?page=" + page,
        				HttpMethod.GET,
        				connection.getHttpEntity(),
        				new ParameterizedTypeReference<ProductsSearchOutputDTO>(){});
        return response.getBody();
    }
    private List<ProductOutputDTO> retrieveAllProducts() {
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
        return Collections.unmodifiableList(retrieveAllProducts());
	}

}
