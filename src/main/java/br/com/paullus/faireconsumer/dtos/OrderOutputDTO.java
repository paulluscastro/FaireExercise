/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.paullus.faireconsumer.enums.OrderState;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class OrderOutputDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private OrderState state;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date ship_after;
	private List<OrderItemOutputDTO> items;
	private List<ShipmentOutputDTO> shipments;
	private AddressOutputDTO address;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date created_at;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date updated_at;
	public String getId() {
		return id;
	}
	public OrderState getState() {
		return state;
	}
	public Date getShip_after() {
		return ship_after;
	}
	public List<OrderItemOutputDTO> getItems() {
		return items;
	}
	public List<ShipmentOutputDTO> getShipments() {
		return shipments;
	}
	public AddressOutputDTO getAddress() {
		return address;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	@Override
	public String toString() {
		return "[id = '" + id + "', State = '" + state + "', Total items = " + items.size() + "]";
	}
	public BigDecimal getTotalOrder() {
		BigDecimal total = items.stream().map(OrderItemOutputDTO::getPrice_cents).reduce(BigDecimal.ZERO, BigDecimal::add);
		return total;
	}
}
