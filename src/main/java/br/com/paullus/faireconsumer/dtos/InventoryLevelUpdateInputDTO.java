/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class InventoryLevelUpdateInputDTO {
	private List<ProductOptionInventoryLevelUpdateInputDTO> inventories = new ArrayList<>();

	public List<ProductOptionInventoryLevelUpdateInputDTO> getInventories() {
		return inventories;
	}
	
	public ProductOptionInventoryLevelUpdateInputDTO add(ProductOptionInventoryLevelUpdateInputDTO option) {
		inventories.add(option);
		return option;
	}
}
