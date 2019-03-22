/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.paullus.faireconsumer.dtos.ShipmentOutputDTO;
import br.com.paullus.faireconsumer.enums.Carrier;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class Shipment implements IFaireEntity {
    private static final Logger logger = LoggerFactory.getLogger(Shipment.class);

    private String id;
	private Order order;
	private BigDecimal makerCost;
	private Carrier carrier;
	private String trackingCode;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public Shipment(Order order, ShipmentOutputDTO dto) {
		id = dto.getId();
		this.order = order;
		makerCost = dto.getMaker_cost_cents().divide(new BigDecimal(100));
		carrier = dto.getCarrier();
		trackingCode = dto.getTracking_code();
		createdAt = LocalDateTime.ofInstant(dto.getCreated_at().toInstant(), ZoneId.systemDefault());
		updatedAt = LocalDateTime.ofInstant(dto.getUpdated_at().toInstant(), ZoneId.systemDefault());
	}
	public String getId() {
		return id;
	}
	public Order getOrder() {
		return order;
	}
	public BigDecimal getMakerCost() {
		return makerCost;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public String getTrackingCode() {
		return trackingCode;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
