/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.paullus.faireconsumer.dtos.OrderItemOutputDTO;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Entity
public class OrderItem implements IFaireEntity {
    @Id
	private String id;
	private Order order;
	private Product product;
	private ProductOption productOption;
	private long quantity;
	private String sku;
	private BigDecimal price;
	private String productName;
	private String productOptionName;
	private boolean includesTester;
	private BigDecimal testerPrice;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public OrderItem(Order order, Product product, ProductOption productOption, OrderItemOutputDTO dto) {
		id = dto.getId();
		this.order = order;
		this.product = product;
		this.productOption = productOption;
		quantity = dto.getQuantity();
		sku = dto.getSku();
		price = dto.getPrice_cents().divide(new BigDecimal(100));
		productName = dto.getProduct_name();
		productOptionName = dto.getProduct_option_name();
		includesTester = dto.isIncludes_tester();
		testerPrice = dto.getTester_price_cents().divide(new BigDecimal(100));
		createdAt = LocalDateTime.ofInstant(dto.getCreated_at().toInstant(), ZoneId.systemDefault());
		updatedAt = LocalDateTime.ofInstant(dto.getUpdated_at().toInstant(), ZoneId.systemDefault());
	}
	public String getId() {
		return id;
	}
	public Order getOrder() {
		return order;
	}
	public Product getProduct() {
		return product;
	}
	public ProductOption getProductOption() {
		return productOption;
	}
	public long getQuantity() {
		return quantity;
	}
	public String getSku() {
		return sku;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductOptionName() {
		return productOptionName;
	}
	public boolean isIncludesTester() {
		return includesTester;
	}
	public BigDecimal getTesterPrice() {
		return testerPrice;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
