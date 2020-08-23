package com.bci.pruebatecnica.data.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4125693264035897365L;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("phones")
	private ArrayList<RequestPhone> phones;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<RequestPhone> getPhones() {
		return phones;
	}
	public void setPhones(ArrayList<RequestPhone> phones) {
		this.phones = phones;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	


}
