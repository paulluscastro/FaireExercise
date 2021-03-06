package br.com.paullus.faireconsumer.services;

import java.util.List;

import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;

public interface IProductService {
	ProductOutputDTO get(String id);
	List<ProductOutputDTO> list();
	List<ProductOutputDTO> listByBrand(String brandId);
}
