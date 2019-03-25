/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class BackorderItemInputDTO {
    @JsonIgnore
    public OrderItemOutputDTO orderItem;
    @JsonIgnore
    public BackorderItemInfoInputDTO info;
    public BackorderItemInputDTO(OrderItemOutputDTO orderItem, long availableQuantity) {
    	this.orderItem = orderItem;
    	info = new BackorderItemInfoInputDTO(availableQuantity);
    }
	public BackorderItemInfoInputDTO getInfo() {
		return info;
	}
    @JsonAnyGetter
    public Map<String, Object> any() {
        return Collections.singletonMap(orderItem.getId(), info);
    }
}
