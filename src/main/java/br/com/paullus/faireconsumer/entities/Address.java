/**
 * 
 */
package br.com.paullus.faireconsumer.entities;

import java.io.Serializable;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String address1;
	private String address2;
	private String postal_code;
	private String city;
	private String state;
	private String state_code;
	private String phone_number;
	private String country;
	private String country_code;
	private String company_name;
	public String getName() {
		return name;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getState_code() {
		return state_code;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public String getCountry() {
		return country;
	}
	public String getCountry_code() {
		return country_code;
	}
	public String getCompany_name() {
		return company_name;
	}
	
}
