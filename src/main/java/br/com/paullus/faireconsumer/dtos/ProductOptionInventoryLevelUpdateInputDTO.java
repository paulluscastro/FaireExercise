/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.time.LocalDateTime;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class ProductOptionInventoryLevelUpdateInputDTO {
	private String sku;
	private long current_quantity;
	private boolean discontinued;
	private LocalDateTime backordered_until;
	public ProductOptionInventoryLevelUpdateInputDTO(String sku, long current_quantity) {
		super();
		this.sku = sku;
		this.current_quantity = current_quantity;
	}
	public ProductOptionInventoryLevelUpdateInputDTO(String sku, long current_quantity, boolean discontinued) {
		super();
		this.sku = sku;
		this.current_quantity = current_quantity;
		this.discontinued = discontinued;
	}
	public ProductOptionInventoryLevelUpdateInputDTO(String sku, long current_quantity, LocalDateTime backordered_until) {
		super();
		this.sku = sku;
		this.current_quantity = current_quantity;
		this.backordered_until = backordered_until;
	}
	public ProductOptionInventoryLevelUpdateInputDTO(String sku, long current_quantity, boolean discontinued, LocalDateTime backordered_until) {
		super();
		this.sku = sku;
		this.current_quantity = current_quantity;
		this.discontinued = discontinued;
		this.backordered_until = backordered_until;
	}
	public String getSku() {
		return sku;
	}
	public long getCurrent_quantity() {
		return current_quantity;
	}
	public boolean isDiscontinued() {
		return discontinued;
	}
	public LocalDateTime getBackordered_until() {
		return backordered_until;
	}

}
