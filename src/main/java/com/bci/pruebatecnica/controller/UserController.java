package com.bci.pruebatecnica.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.pruebatecnica.data.dto.RequestUser;
import com.bci.pruebatecnica.data.dto.Wrapper;
import com.bci.pruebatecnica.services.IUsuarioService;

@RestController
@RequestMapping(value = "/private/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUsuarioService iUsuarioService;
	

	@PostMapping("/login")
	public ResponseEntity<?> saveUser(@RequestBody RequestUser reqUser){
		Map<String, Object> response = new HashMap<>();
		try {
			if(reqUser == null)
				throw new IllegalArgumentException("Argumentos no válidos");
			
			if(reqUser.getName() == null || "".equals(reqUser.getName()))
				throw new IllegalArgumentException("nombre viene nulo o vacio");
			
			if(reqUser.getEmail() == null || "".equals(reqUser.getEmail()))
				throw new IllegalArgumentException("email viene  nulo o vacio");
			
			if(reqUser.getPassword() == null || "".equals(reqUser.getPassword()))
				throw new IllegalArgumentException("password nulo o vacio");
			
			if(reqUser.getPhones() == null || reqUser.getPhones().size() == 0)
				throw new IllegalArgumentException("lista de numeros vienes vacia");
			
			return ResponseEntity.status(HttpStatus.OK).body(iUsuarioService.saveUser(reqUser));
		}catch (IllegalArgumentException e) {
			response.put("mensaje", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}catch (DataAccessException e) {
			response.put("mensaje", e.getMostSpecificCause().getMessage());
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(response);
		}catch (Exception e) {
			logger.error("ERROR - [Metodo - saveUser] ");
			response.put("mensaje", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") long id){
		Map<String, Object> response = new HashMap<>();
		try {
			if(id == 0)
				throw new IllegalArgumentException("Argumentos no válidos");
			
			return ResponseEntity.status(HttpStatus.OK).body(iUsuarioService.getUserById(id));
		}catch (IllegalArgumentException e) {
			response.put("mensaje", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}catch (DataAccessException e) {
			response.put("mensaje", e.getMostSpecificCause().getMessage());
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(response);
		}catch (Exception e) {
			logger.warn("ERROR - [Metodo - getUser] ");
			response.put("mensaje", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@RequestBody RequestUser reqUser,  @PathVariable("id") long id){
		Map<String, Object> response = new HashMap<>();
		try {
			if(reqUser == null)
				throw new IllegalArgumentException("Argumentos no válidos");
			
			if(reqUser.getName() == null || "".equals(reqUser.getName()))
				throw new IllegalArgumentException("nombre viene nulo o vacio");
			
			if(reqUser.getEmail() == null || "".equals(reqUser.getEmail()))
				throw new IllegalArgumentException("email viene  nulo o vacio");
			
			if(reqUser.getPassword() == null || "".equals(reqUser.getPassword()))
				throw new IllegalArgumentException("password nulo o vacio");
			
			Wrapper<String> mensaje = new Wrapper<String>();
			if(iUsuarioService.updateUser(reqUser, id)) 
				mensaje.setMensaje("Se modificaron los datos del usuario correctamente.");
			else
				mensaje.setMensaje("El sistema no puede modificar los datos.");
			
			
			return ResponseEntity.status(HttpStatus.OK).body(mensaje);
		}catch (IllegalArgumentException e) {
			response.put("mensaje", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}catch (DataAccessException e) {
			response.put("mensaje", e.getMostSpecificCause().getMessage());
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(response);
		}catch (Exception e) {
			logger.error("ERROR - [Metodo - updateUser] ", e);
			response.put("mensaje", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	
	@GetMapping("/logout/{id}")
	public ResponseEntity<?> logOutUser(@PathVariable("id") long id){
		Map<String, Object> response = new HashMap<>();
		try {
			if(id == 0)
				throw new IllegalArgumentException("Argumentos no válidos");
			
	
			Wrapper<String> mensaje = new Wrapper<String>();
			if(iUsuarioService.logOutUser(id)) 
				mensaje.setMensaje("cierre de sesión.");
			else
				mensaje.setMensaje("problema para cerrar sesión");
			
			
			return ResponseEntity.status(HttpStatus.OK).body(mensaje);
		}catch (IllegalArgumentException e) {
			response.put("mensaje", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}catch (DataAccessException e) {
			response.put("mensaje", e.getMostSpecificCause().getMessage());
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(response);
		}catch (Exception e) {
			logger.warn("ERROR - [Metodo - updateUser] ");
			response.put("mensaje", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
