package br.com.paullus.faireconsumer.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.paullus.faireconsumer.entities.Product;

public interface IProductRepository extends IGenericRepository<Product>{
	List<Product> listByBrand();
}
