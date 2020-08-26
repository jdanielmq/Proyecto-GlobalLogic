package com.bci.pruebatecnica;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bci.pruebatecnica.jwt.segurity.JWTAuthorizationFilter;
import com.bci.pruebatecnica.jwt.segurity.JwtAuthenticationEntryPoint;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;


@ServletComponentScan
@SpringBootApplication
public class PruebaTecnicaApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PruebaTecnicaApplication.class);
	
	public String key;
	
	@Value("${jwt.secret}")
	public void setKEY_SECRET(String kEY_SECRET) {
		key = kEY_SECRET;
	}

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}
	

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			try {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(key), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.GET,"/h2-console/*").permitAll()
				.antMatchers(HttpMethod.POST, "/private/user/login").permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			}catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException  e ) {
				logger.error("ERROR - [WebSecurityConfig -> Metodo - configure] ",e.getCause());
			}catch (Exception e) {
				logger.error("ERROR - [WebSecurityConfig -> Metodo - configure] ",e.getCause());
			}
		}
		
		
		
	}

}
