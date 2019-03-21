/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String brand_id;
	private String short_description;
	private String description;
	private BigDecimal wholesale_price_cents;
	private BigDecimal retail_price_cents;
	private boolean active;
	private String name;
	private BigDecimal unit_multiplier;
	private List<ProductOption> options;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	public String getId() {
		return id;
	}
	public String getBrand_id() {
		return brand_id;
	}
	public String getShort_description() {
		return short_description;
	}
	public String getDescription() {
		return description;
	}
	public BigDecimal getWholesale_price_cents() {
		return wholesale_price_cents;
	}
	public BigDecimal getRetail_price_cents() {
		return retail_price_cents;
	}
	public boolean isActive() {
		return active;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getUnit_multiplier() {
		return unit_multiplier;
	}
	public List<ProductOption> getOptions() {
		return options;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
}
