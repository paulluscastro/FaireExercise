/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class BestSellerOutputDTO {
	private ProductOptionOutputDTO option;
	private long ammount;
	public BestSellerOutputDTO(ProductOptionOutputDTO option, long ammount) {
		this.option = option;
		this.ammount = ammount;
	}
	public ProductOptionOutputDTO getOption() {
		return option;
	}
	public void add(long quantity) {
		ammount += quantity;
	}
	public long getAmmount() {
		return ammount;
	}
}
