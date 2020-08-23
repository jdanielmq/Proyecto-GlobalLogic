package com.bci.pruebatecnica.data.dto;

import java.io.Serializable;

public class Wrapper<T>  implements Serializable{
	
	private static final long serialVersionUID = -1764598016620660340L;
	private T data;
	
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
