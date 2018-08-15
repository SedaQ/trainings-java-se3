package com.trainings.facade.api.dto.address;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class AddressCreateDTO {

	private String city;
	private String street;
	private Integer houseNumber;
	private String zipCode;

	public AddressCreateDTO() {
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
