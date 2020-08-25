package com.bci.pruebatecnica.controller;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;

import com.bci.pruebatecnica.data.dto.RequestPhone;
import com.bci.pruebatecnica.data.dto.RequestUser;
import com.bci.pruebatecnica.data.dto.ResponseUser;
import com.bci.pruebatecnica.services.IUsuarioService;


public class UserControllerTest {
	
	private static final int SUCCEES_HTTP_OK = 200;
	private static final int ERROR_NOT_FOUND = 404;
	private static final int NOT_ACCEPTABLE = 406;
	private static final int ERROR_INTERNAL_SERVER_ERROR = 500;
	
	Map<String, Object> RESPONSE_MENSAJE_OK = new HashMap<>();
	Map<String, Object> RESPONSE_MENSAJE_NOTOK = new HashMap<>();
	private static final String MENSAJE_OK = "Se modificaron los datos del usuario correctamente.";
	private static final String MENSAJE_NOTOK = "El sistema no puede modificar los datos.";
	private static final String MENSAJE_LOGOUT_OK = "cierre de sesión.";
	private static final String MENSAJE_LOGOUT_NOTOK = "problema para cerrar sesión";
	
	RequestUser REQUEST_USER = new RequestUser();
	
	private static final String NAME = "Juan Daniel Muñoz Queupul";
	private static final String EMAIL = "juandaniel@hotmail.org";
	private static final String PASSWORD = "Hnnn23#Yn";
	private ArrayList<RequestPhone> LISTA_PHONES = new ArrayList<RequestPhone>();
	
	RequestPhone RESQUEST_PHONE = new RequestPhone();
	
	
	private static final String NUMBER = "1234567";
	private static final String CITY_CODE = "1";
	private static final String COUNTRY_CODE ="57";
	
	
	ResponseUser RESPONSE_USER = new ResponseUser();
	private static final Long ID = 1L;
	private static final String CREATED = "24-08-2020 12:48 PM";
	private static final String MODIFIED = "24-08-2020 12:48 PM";
	private static final String LASTLOGIN = "24-08-2020 12:48 PM";
	private static final String TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE1OTgyODc3MDgsImV4cCI6MTU5ODI4ODMwOH0.yZgx9ndxPrKsF1f9nb31ZF7BZfUga3mg1pIkkxYbrGPhc_SQJKq9GbPMUWBqL8ePflXJyzK5Yu-2OU5XYfIzwg";
	private static final boolean ACTIVE = true;	
	
	
	private static final boolean RESPONSE_TRUE = true;
	private static final boolean RESPONSE_FALSE = false;
	
	
	
	@Mock
	IUsuarioService iUsuarioService;
	
	@InjectMocks
	UserController userController;
	
	

	@Before
	public void init() throws Exception, IllegalArgumentException, DataAccessException {
		MockitoAnnotations.initMocks(this);
		
		RESQUEST_PHONE.setCityCode(CITY_CODE);
		RESQUEST_PHONE.setContryCode(COUNTRY_CODE);
		RESQUEST_PHONE.setNumber(NUMBER);
		
		LISTA_PHONES.add(RESQUEST_PHONE);
		
		REQUEST_USER.setEmail(EMAIL);
		REQUEST_USER.setName(NAME);
		REQUEST_USER.setPassword(PASSWORD);
		REQUEST_USER.setPhones(LISTA_PHONES);
		
		RESPONSE_USER.setActive(ACTIVE);
		RESPONSE_USER.setCreated(CREATED);
		RESPONSE_USER.setId(ID);
		RESPONSE_USER.setLastLogin(LASTLOGIN);
		RESPONSE_USER.setModified(MODIFIED);
		RESPONSE_USER.setToken(TOKEN);
		
		RESPONSE_MENSAJE_OK.put("mensaje", MENSAJE_OK);
		RESPONSE_MENSAJE_NOTOK.put("mensaje", MENSAJE_NOTOK);
		
		Mockito.when(iUsuarioService.saveUser(REQUEST_USER)).thenReturn(RESPONSE_USER);
		Mockito.when(iUsuarioService.getUserById(ID)).thenReturn(RESPONSE_USER);
		Mockito.when(iUsuarioService.updateUser(REQUEST_USER, ID)).thenReturn(RESPONSE_TRUE);
		Mockito.when(iUsuarioService.logOutUser(ID)).thenReturn(RESPONSE_TRUE);

		
		
	}
	
	/**
	 * 
	 * Pruebas para el método SaveUser(RequestUser)
	 * 
	 * **/
	
	@Test
	public void saveUserTest(){
		ResponseEntity<?> response = userController.saveUser(REQUEST_USER);
		assertEquals(response.getBody(), RESPONSE_USER);
		assertEquals(response.getStatusCode().value(), SUCCEES_HTTP_OK); 
	}
	
	@Test
	public void saveUserNotFoundNombreTest() {
		RequestUser VALIDAR_NOMBRE = (RequestUser)REQUEST_USER.clone();
		VALIDAR_NOMBRE.setName(null);
		ResponseEntity<?> response = userController.saveUser(VALIDAR_NOMBRE);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}
	
	@Test
	public void saveUserNotFoundEmailTest(){
		RequestUser VALIDAR_EMAIL = (RequestUser)REQUEST_USER.clone();
		VALIDAR_EMAIL.setEmail(null);
		ResponseEntity<?> response = userController.saveUser(VALIDAR_EMAIL);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}
	
	@Test
	public void saveUserNotFoundPasswordTest(){
		RequestUser VALIDAR_PASSWORD = (RequestUser)REQUEST_USER.clone();
		VALIDAR_PASSWORD.setPassword(null);
		ResponseEntity<?> response = userController.saveUser(VALIDAR_PASSWORD);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}

	@Test
	public void saveUserNotFoundListaTest(){
		RequestUser VALIDAR_LISTA = (RequestUser)REQUEST_USER.clone();
		VALIDAR_LISTA.setPhones(new ArrayList<RequestPhone>(0));
		ResponseEntity<?> response = userController.saveUser(VALIDAR_LISTA);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}
	
	
	@Test
	public void saveUserExceptionTest(){
		Mockito.when(userController.saveUser(REQUEST_USER)).thenThrow(new Exception());
		ResponseEntity<?> response = userController.saveUser(REQUEST_USER);
		assertEquals(response.getStatusCode().value(), ERROR_INTERNAL_SERVER_ERROR); 
		
	}
	
	@Test
	public void saveUserNullPointerExceptionTest(){
		Mockito.when(userController.saveUser(REQUEST_USER)).thenThrow(new NullPointerException());
		ResponseEntity<?> response = userController.saveUser(REQUEST_USER);
		assertEquals(response.getStatusCode().value(), NOT_ACCEPTABLE); 
		
	}
	
	
	
	
	
	/**
	 * 
	 * Pruebas para el método getUser(Long id)
	 * 
	 * **/
	
	@Test
	public void getUserTest(){
		ResponseEntity<?> response = userController.getUser(ID);
		assertEquals(response.getBody(), RESPONSE_USER);
		assertEquals(response.getStatusCode().value(), SUCCEES_HTTP_OK); 
	}
	
	@Test
	public void getUserNotFoundIdTest() {
		long ID = 0;
		ResponseEntity<?> response = userController.getUser(ID);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}
	
	
	@Test
	public void getUserExceptionTest(){
		Mockito.when(userController.getUser(ID)).thenThrow(new Exception());
		ResponseEntity<?> response = userController.getUser(ID);
		assertEquals(response.getStatusCode().value(), ERROR_INTERNAL_SERVER_ERROR); 
		
	}
	
	@Test
	public void getUserNullPointerExceptionTest(){
		Mockito.when(userController.getUser(ID)).thenThrow(new NullPointerException());
		ResponseEntity<?> response = userController.getUser(ID);
		assertEquals(response.getStatusCode().value(), NOT_ACCEPTABLE); 
		
	}
	
	
	
	/**
	 * 
	 * Pruebas para el método updateUser(REQUEST_USER,ID)
	 * 
	 * **/
	
	
	
	@Test
	public void updateUserTest(){
		ResponseEntity<?> response = userController.updateUser(REQUEST_USER,ID);
		assertEquals(response.getBody().toString(), RESPONSE_MENSAJE_OK.toString());
		assertEquals(response.getStatusCode().value(), SUCCEES_HTTP_OK); 
	}
	
	@Test
	public void updateUserFalseTest() throws IllegalArgumentException, NullPointerException, Exception{
		Mockito.when(iUsuarioService.updateUser(REQUEST_USER, ID)).thenReturn(RESPONSE_FALSE);
		ResponseEntity<?> response = userController.updateUser(REQUEST_USER,ID);
		assertEquals(response.getBody().toString(), RESPONSE_MENSAJE_NOTOK.toString());
		assertEquals(response.getStatusCode().value(), SUCCEES_HTTP_OK); 
	}
	
	@Test
	public void updateUserNotFoundNombreTest() {
		RequestUser VALIDAR_NOMBRE = (RequestUser)REQUEST_USER.clone();
		VALIDAR_NOMBRE.setName(null);
		ResponseEntity<?> response = userController.updateUser(VALIDAR_NOMBRE, ID);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}
	
	@Test
	public void updateUserNotFoundEmailTest(){
		RequestUser VALIDAR_EMAIL = (RequestUser)REQUEST_USER.clone();
		VALIDAR_EMAIL.setEmail(null);
		ResponseEntity<?> response = userController.updateUser(VALIDAR_EMAIL, ID);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}
	
	@Test
	public void updateUserNotFoundPasswordTest(){
		RequestUser VALIDAR_PASSWORD = (RequestUser)REQUEST_USER.clone();
		VALIDAR_PASSWORD.setPassword(null);
		ResponseEntity<?> response = userController.updateUser(VALIDAR_PASSWORD, ID);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}
	
	
	@Test
	public void updateUserNotFoundTest() {
		long ID = 0;
		ResponseEntity<?> response = userController.updateUser(REQUEST_USER, ID);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}

	
	@Test
	public void updateUserExceptionTest(){
		Mockito.when(userController.updateUser(REQUEST_USER,ID)).thenThrow(new Exception());
		ResponseEntity<?> response = userController.updateUser(REQUEST_USER, ID);
		assertEquals(response.getStatusCode().value(), ERROR_INTERNAL_SERVER_ERROR); 
		
	}
	
	@Test
	public void updateUserNullPointerExceptionTest(){
		Mockito.when(userController.updateUser(REQUEST_USER,ID)).thenThrow(new NullPointerException());
		ResponseEntity<?> response = userController.updateUser(REQUEST_USER, ID);
		assertEquals(response.getStatusCode().value(), NOT_ACCEPTABLE); 
		
	}
	

	
	
	
	/**
	 * 
	 * Pruebas para el método logOutUser(ID)
	 * 
	 * **/
	@Test
	public void logOutUserTest(){
		RESPONSE_MENSAJE_OK.clear();
		RESPONSE_MENSAJE_OK.put("mensaje", MENSAJE_LOGOUT_OK);
		ResponseEntity<?> response = userController.logOutUser(ID);
		assertEquals(response.getBody().toString(), RESPONSE_MENSAJE_OK.toString());
		assertEquals(response.getStatusCode().value(), SUCCEES_HTTP_OK); 
	}
	
	@Test
	public void logOutUserFalseTest() throws IllegalArgumentException, NullPointerException, Exception{
		RESPONSE_MENSAJE_NOTOK.clear();
		RESPONSE_MENSAJE_NOTOK.put("mensaje", MENSAJE_LOGOUT_NOTOK);
		Mockito.when(iUsuarioService.logOutUser(ID)).thenReturn(RESPONSE_FALSE);
		ResponseEntity<?> response = userController.logOutUser(ID);
		assertEquals(response.getBody().toString(), RESPONSE_MENSAJE_NOTOK.toString());
		assertEquals(response.getStatusCode().value(), SUCCEES_HTTP_OK); 
	}
	
	
	@Test
	public void logOutUserNotFoundTest() {
		long ID = 0;
		ResponseEntity<?> response = userController.logOutUser(ID);
		assertEquals(response.getStatusCode().value(), ERROR_NOT_FOUND); 
	}
	
	@Test
	public void logOutUserExceptionTest(){
		Mockito.when(userController.logOutUser(ID)).thenThrow(new Exception());
		ResponseEntity<?> response = userController.logOutUser(ID);
		assertEquals(response.getStatusCode().value(), ERROR_INTERNAL_SERVER_ERROR); 
		
	}
	
	@Test
	public void logOutUserNullPointerExceptionTest(){
		Mockito.when(userController.logOutUser(ID)).thenThrow(new NullPointerException());
		ResponseEntity<?> response = userController.logOutUser(ID);
		assertEquals(response.getStatusCode().value(), NOT_ACCEPTABLE); 
		
	}


	

}
