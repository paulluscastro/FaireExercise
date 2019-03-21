/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class ProductOption implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String product_id;
	private boolean active;
	private String name;
	private String sku;
	private int available_quantity;
	private LocalDateTime backordered_until;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	public String getId() {
		return id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public boolean isActive() {
		return active;
	}
	public String getName() {
		return name;
	}
	public String getSku() {
		return sku;
	}
	public int getAvailable_quantity() {
		return available_quantity;
	}
	public LocalDateTime getBackordered_until() {
		return backordered_until;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
}