/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.paullus.faireconsumer.dtos.AddressOutputDTO;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class Address implements IFaireEntity {
    private static final Logger logger = LoggerFactory.getLogger(Address.class);
	
	private String id;
	private String name;
	private String address1;
	private String address2;
	private String postalCode;
	private String city;
	private String state;
	private String stateCode;
	private String phoneNumber;
	private String country;
	private String countryCode;
	private String companyName;
	public Address(AddressOutputDTO dto) {
		id = "ad_" + UUID.randomUUID().toString();
		name = dto.getName();
		address1 = dto.getAddress1();
		address2 = dto.getAddress2();
		postalCode = dto.getPostal_code();
		city = dto.getCity();
		state = dto.getState();
		stateCode = dto.getState_code();
		phoneNumber = dto.getPhone_number();
		country = dto.getCountry();
		countryCode = dto.getCountry_code();
		companyName = dto.getCompany_name();
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getStateCode() {
		return stateCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getCountry() {
		return country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public String getCompanyName() {
		return companyName;
	}
}
