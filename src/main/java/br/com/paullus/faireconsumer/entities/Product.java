/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import br.com.paullus.faireconsumer.dtos.ProductOutputDTO;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class Product implements IFaireEntity {
	private String id;
	private String brandId;
	private String shortDescription;
	private String description;
	private BigDecimal wholesalePrice;
	private BigDecimal retailPrice;
	private boolean active;
	private String name;
	private BigDecimal unitMultiplier;
	private List<ProductOption> options;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public Product(ProductOutputDTO dto) {
		id = dto.getId();
		brandId = dto.getBrand_id();
		shortDescription = dto.getShort_description();
		description = dto.getDescription();
		wholesalePrice = dto.getWholesale_price_cents().divide(new BigDecimal(100));
		retailPrice = dto.getRetail_price_cents().divide(new BigDecimal(100));
		active = dto.isActive();
		name = dto.getName();
		unitMultiplier = dto.getUnit_multiplier();
		createdAt = LocalDateTime.ofInstant(dto.getCreated_at().toInstant(), ZoneId.systemDefault());
		updatedAt = LocalDateTime.ofInstant(dto.getUpdated_at().toInstant(), ZoneId.systemDefault());
		options = new ArrayList<>();
		dto.getOptions().stream().forEach(po -> options.add(new ProductOption(this, po)));
	}
	
	public String getId() {
		return id;
	}
	public String getBrandId() {
		return brandId;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public String getDescription() {
		return description;
	}
	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}
	public boolean isActive() {
		return active;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getUnitMultiplier() {
		return unitMultiplier;
	}
	public List<ProductOption> getOptions() {
		return options;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	@Override
	public String toString() {
		return "[Id: '" + id + "', Brand_id = '" + brandId + "', Name: '" + name + "', Short description: '" + shortDescription + "']";
	}
}
