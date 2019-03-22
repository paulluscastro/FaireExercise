package br.com.paullus.faireconsumer.services;

import java.util.List;

import br.com.paullus.faireconsumer.entities.Product;

public interface IProductService {
	Product get(String id);
	List<Product> list();
	List<Product> listByBrand(String brandId);
}
