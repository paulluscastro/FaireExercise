/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.math.BigDecimal;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class BestSellerOutputDTO {
	private ProductOutputDTO product;
	private ProductOptionOutputDTO option;
	private BigDecimal ammount;
	public BestSellerOutputDTO(ProductOutputDTO product, ProductOptionOutputDTO option, long ammount) {
		this.product = product;
		this.option = option;
		this.ammount = new BigDecimal(ammount);
	}
	public ProductOptionOutputDTO getOption() {
		return option;
	}
	public ProductOutputDTO getProduct() {
		return product;
	}
	public void add(BigDecimal quantity) {
		ammount = ammount.add(quantity);
	}
	public BigDecimal getAmmount() {
		return ammount;
	}
}
