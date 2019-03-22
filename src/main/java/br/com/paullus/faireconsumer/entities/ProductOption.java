/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.paullus.faireconsumer.dtos.ProductOptionOutputDTO;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Entity
public class ProductOption implements IFaireEntity {
    private static final Logger logger = LoggerFactory.getLogger(ProductOption.class);

    @Id
    private String id;
    @ManyToOne
	private Product product;
	private boolean active;
	private String name;
	private String sku;
	private long availableQuantity;
	private LocalDateTime backorderedUntil;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public ProductOption(Product product, ProductOptionOutputDTO dto) {
		id = dto.getId();
		this.product = product;
		active = dto.isActive();
		name = dto.getName();
		sku = dto.getSku();
		availableQuantity = dto.getAvailable_quantity();
		if (dto.getBackordered_until() != null)
			backorderedUntil = LocalDateTime.ofInstant(dto.getBackordered_until().toInstant(), ZoneId.systemDefault());
		createdAt = LocalDateTime.ofInstant(dto.getCreated_at().toInstant(), ZoneId.systemDefault());
		updatedAt = LocalDateTime.ofInstant(dto.getUpdated_at().toInstant(), ZoneId.systemDefault());
	}

	public String getId() {
		return id;
	}
	public Product getProduct() {
		return product;
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
	public long getAvailableQuantity() {
		return availableQuantity;
	}
	public LocalDateTime getBackorderedUntil() {
		return backorderedUntil;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}