package com.bci.pruebatecnica.data.dto;

import java.io.Serializable;

public class ErrorDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public ErrorDto(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
