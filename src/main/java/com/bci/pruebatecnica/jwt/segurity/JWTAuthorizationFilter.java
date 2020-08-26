package com.bci.pruebatecnica.jwt.segurity;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;


public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	
	private String key;
	
	public JWTAuthorizationFilter(String key) {
		this.key = key;
	}

	/**
	 *  Metodo que se dispara cada vez que se hace una pettición a la API
	 *  
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param FilterChain
	 * @return void
	 * @exception ServletException, IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			if (existeJWTToken(request, response)) {
				Claims claims = validateToken(request);
				if(claims == null) {
			        response.addHeader("WWW-Authenticate", "Basic realm= Not ".concat(HEADER));
			        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "".concat(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED)));
			        return;
				}
					
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
					//response.set
				}
			} else {
					SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException  e ) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
			return;
		}
		
	}
	
	/**
	 *  Metodo que obtienen los CLaims {"Reclamaciones"}
	 *  
	 * @param HttpServletRequest
	 * @return Claims
	 * @exception Exception
	 */	
	private Claims validateToken(HttpServletRequest request) {
		try {
			String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
			return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwtToken).getBody();			
		}catch (Exception e) {
			logger.error("Error de expiracion del token", e.getCause());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metodo para autenticarnos dentro del flujo de Spring
	 * 
	 * @param claims
	 * @return void
	 * @exception 
	 */
	private void setUpSpringAuthentication(Claims claims) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List<String>) claims.get("authorities");

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}
	
	
	/**
	 *  Metodo que verifica el cabecera(HEADER) y el prefijo(PREFIX)
	 *  
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return booelan
	 * @exception 
	 */	
	private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
}
