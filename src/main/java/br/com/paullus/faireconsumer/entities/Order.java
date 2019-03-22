/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.paullus.faireconsumer.enums.OrderState;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class Order implements Serializable, IFaireEntity {
	private static final long serialVersionUID = 1L;

	private String id;
	private OrderState state;
	private String ship_after;
	private List<OrderItem> items;
	private List<Shipment> shipments;
	private Address address;
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
	public String getShip_after() {
		return ship_after;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public List<Shipment> getShipments() {
		return shipments;
	}
	public Address getAddress() {
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
}
