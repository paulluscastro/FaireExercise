/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.util.Date;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class BackorderItemInfoInputDTO {
	private long available_quantity;
    private boolean discontinued;
    private Date backordered_until;
    public BackorderItemInfoInputDTO(long quantity) {
    	this.available_quantity = quantity;
    }
	public long getAvailable_quantity() {
		return available_quantity;
	}
	public boolean isDiscontinued() {
		return discontinued;
	}
	public Date getBackordered_until() {
		return backordered_until;
	}
}
