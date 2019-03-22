/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.paullus.faireconsumer.dtos.OrderOutputDTO;
import br.com.paullus.faireconsumer.enums.OrderState;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
@Entity
public class Order implements IFaireEntity {
    @Id
	private String id;
	private OrderState state;
	private LocalDateTime shipAfter;
	private List<OrderItem> items;
	private List<Shipment> shipments;
	private Address address;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public Order(OrderOutputDTO dto) {
		id = dto.getId();
		state = dto.getState();
		if (dto.getShip_after() != null)
			shipAfter = LocalDateTime.ofInstant(dto.getShip_after().toInstant(), ZoneId.systemDefault());
		items = new ArrayList<>();
		shipments = new ArrayList<>();
		address = new Address(dto.getAddress());
		createdAt = LocalDateTime.ofInstant(dto.getCreated_at().toInstant(), ZoneId.systemDefault());
		updatedAt = LocalDateTime.ofInstant(dto.getUpdated_at().toInstant(), ZoneId.systemDefault());
	}
	public String getId() {
		return id;
	}
	public OrderState getState() {
		return state;
	}
	public LocalDateTime getShipAfter() {
		return shipAfter;
	}
	public List<OrderItem> getItems() {
		return Collections.unmodifiableList(items);
	}
	public List<Shipment> getShipments() {
		return shipments;
	}
	public Address getAddress() {
		return address;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public OrderItem addItem(OrderItem item) {
		items.add(item);
		return item;
	}
	@Override
	public String toString() {
		return "[id = '" + id + "', State = '" + state + "', Total items = " + items.size() + "]";
	}
}
