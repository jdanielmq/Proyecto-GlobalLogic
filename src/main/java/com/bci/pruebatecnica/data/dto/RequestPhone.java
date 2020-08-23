package com.bci.pruebatecnica.data.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestPhone implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5774237524886724627L;
	@JsonProperty("number")
	private String number;
	@JsonProperty("citycode")
	private String cityCode;
	@JsonProperty("contrycode")
	private String contryCode;
	
	
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
	public String getContryCode() {
		return contryCode;
	}
	public void setContryCode(String contryCode) {
		this.contryCode = contryCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
