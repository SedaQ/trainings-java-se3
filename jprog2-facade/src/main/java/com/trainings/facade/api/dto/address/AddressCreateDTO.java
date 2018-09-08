package com.trainings.facade.api.dto.address;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class AddressCreateDTO {

	@NotEmpty(message = "{address.city.NotEmpty.message}")
	private String city;
	@NotEmpty(message = "{address.street.NotEmpty.message}")
	private String street;
	@NotNull(message = "{address.house.number.NotNull.message}")
	private Integer houseNumber;
	@NotEmpty(message = "{address.zip.code.NotEmpty.message}")
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressCreateDTO [city=");
		builder.append(city);
		builder.append(", street=");
		builder.append(street);
		builder.append(", houseNumber=");
		builder.append(houseNumber);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append("]");
		return builder.toString();
	}

}
