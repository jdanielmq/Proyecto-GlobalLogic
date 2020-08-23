package com.bci.pruebatecnica.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.bci.pruebatecnica.data.dto.ResponseUser;
import com.bci.pruebatecnica.data.dto.Wrapper;
import com.bci.pruebatecnica.services.IUsuarioService;

@RestController
@RequestMapping(value = "/private/user")
@CrossOrigin(origins = "*")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUsuarioService iUsuarioService;
	

	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity<Wrapper> saveUser(@RequestBody RequestUser reqUser){
		Wrapper<ResponseUser> response = new Wrapper<>();
		try {
			if(reqUser == null)
				throw new Exception("Argumentos no válidos");
			
			if(reqUser.getName() == null || "".equals(reqUser.getName()))
				throw new Exception("nombre viene nulo o vacio");
			
			if(reqUser.getEmail() == null || "".equals(reqUser.getEmail()))
				throw new Exception("email viene  nulo o vacio");
			
			if(reqUser.getPassword() == null || "".equals(reqUser.getPassword()))
				throw new Exception("password nulo o vacio");
			
			if(reqUser.getPhones() == null || reqUser.getPhones().size() == 0)
				throw new Exception("lista de numeros vienes vacia");
			
			
			response.setData(iUsuarioService.saveUser(reqUser));
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}catch (Exception e) {
			logger.warn("ERROR - [Metodo - saveUser] ");
			Wrapper<String> mensaje = new Wrapper<String>(); 
			mensaje.setData(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity<Wrapper> getUser(@PathVariable("id") long id){
		Wrapper<ResponseUser> response = new Wrapper<>();
		try {
			if(id == 0)
				throw new Exception("Argumentos no válidos");
			
			response.setData(iUsuarioService.getUserById(id));
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}catch (Exception e) {
			logger.warn("ERROR - [Metodo - getUser] ");
			Wrapper<String> mensaje = new Wrapper<String>(); 
			mensaje.setData(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/update/{id}")
	public ResponseEntity<Wrapper> updateUser(@RequestBody RequestUser reqUser,  @PathVariable("id") long id){
		try {
			if(reqUser == null)
				throw new Exception("Argumentos no válidos");
			
			if(reqUser.getName() == null || "".equals(reqUser.getName()))
				throw new Exception("nombre viene nulo o vacio");
			
			if(reqUser.getEmail() == null || "".equals(reqUser.getEmail()))
				throw new Exception("email viene  nulo o vacio");
			
			if(reqUser.getPassword() == null || "".equals(reqUser.getPassword()))
				throw new Exception("password nulo o vacio");
			
			Wrapper<String> mensaje = new Wrapper<String>();
			if(iUsuarioService.updateUser(reqUser, id)) 
				mensaje.setData("Se modificaron los datos del usuario correctamente.");
			else
				mensaje.setData("El sistema no puede modificar los datos.");
			
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
		}catch (Exception e) {
			logger.warn("ERROR - [Metodo - updateUser] ");
			Wrapper<String> mensaje = new Wrapper<String>(); 
			mensaje.setData(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
		}
	}

	
	@SuppressWarnings("rawtypes")
	@GetMapping("/logout/{id}")
	public ResponseEntity<Wrapper> logOutUser(@PathVariable("id") long id){
		try {
			if(id == 0)
				throw new Exception("Argumentos no válidos");
			
	
			Wrapper<String> mensaje = new Wrapper<String>();
			if(iUsuarioService.logOutUser(id)) 
				mensaje.setData("cierre de sesiòn.");
			else
				mensaje.setData("problema para cerrar sesion");
			
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
		}catch (Exception e) {
			logger.warn("ERROR - [Metodo - updateUser] ");
			Wrapper<String> mensaje = new Wrapper<String>(); 
			mensaje.setData(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
		}
	}
	
}
