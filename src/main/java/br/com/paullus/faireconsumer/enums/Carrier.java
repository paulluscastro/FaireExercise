package br.com.paullus.faireconsumer.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Carrier {
	CANADA_POST,
	DHL_ECOMMERCE,
	DHL_EXPRESS,
	FEDEX,
	PUROLATOR,
	UPS,
	USPS,
	POSTNL
}
