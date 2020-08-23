package com.bci.pruebatecnica.data.dto;

import java.io.Serializable;

import com.bci.pruebatecnica.data.funcionales.DtoEntity;

public class PhoneDto implements Serializable, DtoEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8229778576841702038L;
	private Long idPhone;
	private String number;
	private String cityCode;
	private String countryCode;
	private UserDto userDto;
	
	
	public Long getIdPhone() {
		return idPhone;
	}
	public void setIdPhone(Long idPhone) {
		this.idPhone = idPhone;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
