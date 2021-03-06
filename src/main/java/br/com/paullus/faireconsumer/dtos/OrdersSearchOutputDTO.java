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
public class OrdersSearchOutputDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<OrderOutputDTO> orders;
	private int page;
	private int limit;
	public List<OrderOutputDTO> getOrders() {
		return orders;
	}
	public int getPage() {
		return page;
	}
	public int getLimit() {
		return limit;
	}
}
