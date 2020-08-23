package com.bci.pruebatecnica;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bci.pruebatecnica.jwt.segurity.JWTAuthorizationFilter;
import com.bci.pruebatecnica.services.impl.UsuarioServiceImpl;


@ServletComponentScan
@SpringBootApplication
public class PruebaTecnicaApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(PruebaTecnicaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecnicaApplication.class, args);
	}
	

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			try {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.GET,"/h2-console/").permitAll()
				.antMatchers(HttpMethod.POST, "/private/user/login").permitAll()
				.anyRequest().authenticated();
			}catch (Exception e) {
				logger.error("ERROR - [WebSecurityConfig -> Metodo - configure] ");
			}
		}
	}

}
