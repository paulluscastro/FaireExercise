/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * @author 1511 IRON
 *
 */
public class ProductsSearchOutputDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ProductOutputDTO> products;
	private int page;
	private int limit;
	public List<ProductOutputDTO> getProducts() {
		return products;
	}
	public int getPage() {
		return page;
	}
	public int getLimit() {
		return limit;
	}
}
