/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class BestBuyerOutputDTO {
	private String state;
	private long ammount;
	public BestBuyerOutputDTO(String state, long ammount) {
		this.state = state;
		this.ammount = ammount;
	}
	public String getState() {
		return state;
	}
	public void add(long quantity) {
		ammount += quantity;
	}
	public long getAmmount() {
		return ammount;
	}
}
