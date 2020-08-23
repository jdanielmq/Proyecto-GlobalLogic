package com.bci.pruebatecnica.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Fecha {
	
	public static final String FORMAT_DDMMYYYY_HHMM_A = "dd'-'MM'-'YYYY' 'HH:mm a";
	
	public static String getLocalDateTimeString(LocalDateTime date) throws Exception {
		try {
			Locale locale = new Locale("es", "CL");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DDMMYYYY_HHMM_A, locale);
			return date.format(formatter);			
			
		}catch (Exception e) {
			throw new Exception("Error el metodo de formateo de fechas");
		}
	}
	

}
