package br.com.paullus.faireconsumer.services;

import java.util.List;
import br.com.paullus.faireconsumer.entities.ProductOption;

public interface IProductOptionService {
	ProductOption create(ProductOption productOption);
	ProductOption get(String id);
	List<ProductOption> listByProductId(String productId);
}
