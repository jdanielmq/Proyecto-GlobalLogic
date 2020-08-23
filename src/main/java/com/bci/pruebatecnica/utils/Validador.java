package com.bci.pruebatecnica.utils;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



public class Validador {
	public static final String EXP_REG_EMAIL = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.([a-zA-Z]{2,4})+$"; 
	public static final String EXP_REG_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"; 
	                                               

	public static final String EMAIL_NO_VALIDO = "El correo no cumple con el formato de Ejemplo: juan@rodriguez.org"; 
	public static final String PASSWORD_NO_VALIDA = "La clave no cumple con el formato de Mayùsculas, Minùsculas, Nùmeros y Simbolos";
	
	private static final String SECRET = "implementando-seguridad-con-jwt";
	
	
	

	public  static String validarEmail(String email) throws Exception {
		try {
			Pattern pattern = Pattern.compile(EXP_REG_EMAIL);
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches())
			     return EMAIL_NO_VALIDO;		
		
			return null ;
		}catch (Exception e) {
			throw new Exception("Error, en la validaciòn del email",e.getCause());
		}
	}

	public  static String validarPassword(String password) throws Exception {
		try {
			Pattern pattern = Pattern.compile(EXP_REG_PASSWORD);
			Matcher matcher = pattern.matcher(password);
			if (!matcher.matches())
			     return PASSWORD_NO_VALIDA;		
		
			return null ;
		}catch (Exception e) {
			throw new Exception("Error, en la validaciòn de la password.",e.getCause());
		}
	}
	
	
	public static String getJWTToken(String username) {
		String secretKey = SECRET;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	

}
