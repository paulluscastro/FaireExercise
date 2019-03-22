package br.com.paullus.faireconsumer.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum OrderState {
	NEW,
	PROCESSING,
	PRE_TRANSIT,
	IN_TRANSIT,
	DELIVERED,
	BACKORDERED,
	CANCELED
}
