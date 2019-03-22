/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class ProductOption implements Serializable, IFaireEntity {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String product_id;
	private boolean active;
	private String name;
	private String sku;
	private int available_quantity;
	private Date backordered_until;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date created_at;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date updated_at;
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
	public Date getBackordered_until() {
		return backordered_until;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
}