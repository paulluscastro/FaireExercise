/**
 * 
 */
package br.com.paullus.faireconsumer.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;
import br.com.paullus.faireconsumer.repositories.IProductRepository;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Service
public class ProductService implements IProductService {
	@Autowired
	private IProductRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Override
	public List<ProductOutputDTO> list() {
        return Collections.unmodifiableList(repository.list());
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
}
