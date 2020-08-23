package com.bci.pruebatecnica.data.dto;

import java.io.Serializable;

public class Wrapper<T>  implements Serializable{
	
	private static final long serialVersionUID = -1764598016620660340L;
	private T mensaje;
	
	

	public T getMensaje() {
		return mensaje;
	}



	public void setMensaje(T mensaje) {
		this.mensaje = mensaje;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
