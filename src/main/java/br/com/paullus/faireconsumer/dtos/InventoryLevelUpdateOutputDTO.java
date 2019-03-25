/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.io.Serializable;
import java.util.List;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class InventoryLevelUpdateOutputDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ProductOptionInventoryLevelUpdateOutputDTO> options;

	public List<ProductOptionInventoryLevelUpdateOutputDTO> getInventories() {
		return options;
	}
	
	public ProductOptionInventoryLevelUpdateOutputDTO add(ProductOptionInventoryLevelUpdateOutputDTO option) {
		options.add(option);
		return option;
	}
}
