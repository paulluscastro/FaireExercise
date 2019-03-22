/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Paullus Martis de Sousa Nava Castro
 *
 */
public class OrderItemOutputDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String order_id;
	private String product_id;
	private String product_option_id;
	private long quantity;
	private String sku;
	private BigDecimal price_cents;
	private String product_name;
	private String product_option_name;
	private boolean includes_tester;
	private BigDecimal tester_price_cents;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date created_at;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date updated_at;
	public String getId() {
		return id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public String getProduct_option_id() {
		return product_option_id;
	}
	public long getQuantity() {
		return quantity;
	}
	public String getSku() {
		return sku;
	}
	public BigDecimal getPrice_cents() {
		return price_cents;
	}
	public String getProduct_name() {
		return product_name;
	}
	public String getProduct_option_name() {
		return product_option_name;
	}
	public boolean isIncludes_tester() {
		return includes_tester;
	}
	public BigDecimal getTester_price_cents() {
		return tester_price_cents;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	
}
