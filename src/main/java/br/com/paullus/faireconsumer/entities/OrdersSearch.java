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
public class OrdersSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Order> orders;
	private int page;
	private int limit;
	public List<Order> getOrders() {
		return orders;
	}
	public int getPage() {
		return page;
	}
	public int getLimit() {
		return limit;
	}
}
