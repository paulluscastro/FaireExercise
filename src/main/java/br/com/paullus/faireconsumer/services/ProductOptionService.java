/**
 * 
 */
package br.com.paullus.faireconsumer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paullus.faireconsumer.entities.ProductOption;
import br.com.paullus.faireconsumer.repositories.IGenericRepository;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Service
public class ProductOptionService implements IProductOptionService {
	@Autowired
	private IGenericRepository<ProductOption> productsOptionRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProductOptionService.class);
	@Override
	public ProductOption create(ProductOption productOption) {
        return productsOptionRepository.create(productOption);
	}
	@Override
	public ProductOption get(String id) {
        logger.info("Find product by id: '" + id + "'");
        ProductOption productOption = productsOptionRepository.get(id);
        if (productOption == null)
        	logger.info("Product not found...");
		return productOption; 
	}
	@Override
	public List<ProductOption> listByProductId(String productId) {
        logger.info("Listing products of brand " + productId + "...");
		List<ProductOption> byProduct = productsOptionRepository.list().stream().filter(po -> po.getProduct().getId().equals(productId)).collect(Collectors.toList());
        logger.info("Listed " + byProduct.size() + " products of brand " + productId + "...");
		return byProduct; 
	}
	
}
