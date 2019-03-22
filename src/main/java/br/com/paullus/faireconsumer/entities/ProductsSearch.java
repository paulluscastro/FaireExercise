/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.io.Serializable;
import java.util.List;

/**
 * @author 1511 IRON
 *
 */
public class ProductsSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Product> products;
	private int page;
	private int limit;
	public List<Product> getProducts() {
		return products;
	}
	public int getPage() {
		return page;
	}
	public int getLimit() {
		return limit;
	}
}
