package com.bci.pruebatecnica.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class Validador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2879458151678540854L;

	public static final String EXP_REG_EMAIL = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.([a-zA-Z]{2,4})+$"; 
	public static final String EXP_REG_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"; 
	public static final String EMAIL_NO_VALIDO = "El correo no cumple con el formato de Ejemplo: juan@rodriguez.org"; 
	public static final String PASSWORD_NO_VALIDA = "La clave no cumple con el formato de Mayùsculas, Minùsculas, Nùmeros y Simbolos";
	

	/**
	 *  Metodo que válida la email con el formato correcto
	 *  
	 * @param password
	 */
	public  static String validarEmail(String email) {
			Pattern pattern = Pattern.compile(EXP_REG_EMAIL);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches())
			     return EMAIL_NO_VALIDO;		
		
			return null ;
	}
	
	/**
	 *  Metodo que válida la passsword con el formato correcto
	 *  
	 * @param password
	 */
	public  static String validarPassword(String password) {
		Pattern pattern = Pattern.compile(EXP_REG_PASSWORD);
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches())
		     return PASSWORD_NO_VALIDA;		
	
		return null ;
	}
	
	/**
	 *  Metodo que genera el Token con el prefijo
	 *  
	 * @param id
	 */
	
	public static String getJWTToken(String id,String key) {
		String secretKey = key;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(id)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000)) // 14000000L
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

}
