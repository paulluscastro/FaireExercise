/**
 * 
 */
package br.com.paullus.faireconsumer.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.paullus.faireconsumer.enums.Carrier;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class ShipmentOutputDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String order_id;
	private BigDecimal maker_cost_cents;
	private Carrier carrier;
	private String tracking_code;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date created_at;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd'T'HHmmss.SSS'Z'")
	private Date updated_at;
	public String getId() {
		return id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public BigDecimal getMaker_cost_cents() {
		return maker_cost_cents;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public String getTracking_code() {
		return tracking_code;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
}
