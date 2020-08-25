package com.bci.pruebatecnica.services.impl;


import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import com.bci.pruebatecnica.data.dataAccess.IPhoneDataAccess;
import com.bci.pruebatecnica.data.dataAccess.IUserDataAcces;
import com.bci.pruebatecnica.data.dto.RequestPhone;
import com.bci.pruebatecnica.data.dto.RequestUser;
import com.bci.pruebatecnica.data.dto.UserDto;
import com.bci.pruebatecnica.data.entity.Phone;
import com.bci.pruebatecnica.data.entity.User;
import com.bci.pruebatecnica.utils.Mapper;
import com.bci.pruebatecnica.utils.Validador;

public class UsuarioServiceImplTest {
	
	
	UserDto USER_DTO = new UserDto();
	RequestUser REQUEST_USER = new RequestUser();
	RequestPhone RESQUEST_PHONE = new RequestPhone();
	private ArrayList<RequestPhone> LISTA_PHONES = new ArrayList<RequestPhone>();
	
	Predicate<RequestPhone> PREDICATE =  new Predicate<RequestPhone>() {
		
		@Override
		public boolean test(RequestPhone t) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	
	Phone PHONE = new Phone();
	private ArrayList<Phone> LISTA_PHONE = new ArrayList<Phone>();
	
	
	private static final String NUMBER = "1234567";
	private static final String CITY_CODE = "1";
	private static final String COUNTRY_CODE ="57";
	
	
	private static final Long ID= 1L;  
	private static final String NAME = "Juan Daniel Muñoz Queupul";
	private static final String EMAIL = "juandaniel@hotmail.org";
	private static final String PASSWORD = "Hnnn23#Yn";
	private static final boolean ACTIVE = true;	
	private static final String TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoiMSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE1OTgyODc3MDgsImV4cCI6MTU5ODI4ODMwOH0.yZgx9ndxPrKsF1f9nb31ZF7BZfUga3mg1pIkkxYbrGPhc_SQJKq9GbPMUWBqL8ePflXJyzK5Yu-2OU5XYfIzwg";

	
	private static final boolean RESPONSE_TRUE = true;
	private static final boolean RESPONSE_FALSE = false;
	
;
	
	



	@Mock
	IUserDataAcces iUserDataAcces;
	
	@Mock 
	IPhoneDataAccess iIPhoneDataAccess;
	
	@Mock
	Mapper mapper;
	
	@Mock
	Validador validador;
	
	@InjectMocks
	UsuarioServiceImpl usuarioServiceImpl;
	
	
	

	
	
	@Before
	public void init() throws Exception, IllegalArgumentException, DataAccessException {
		MockitoAnnotations.initMocks(this);
		
		PHONE.setCityCode(CITY_CODE);
		PHONE.setCountryCode(COUNTRY_CODE);
		PHONE.setNumber(NUMBER);
		PHONE.setUser(new User(ID));
		LISTA_PHONE.add(PHONE);
		
		RESQUEST_PHONE.setCityCode(CITY_CODE);
		RESQUEST_PHONE.setContryCode(COUNTRY_CODE);
		RESQUEST_PHONE.setNumber(NUMBER);
		LISTA_PHONES.add(RESQUEST_PHONE);
		
		USER_DTO.setActive(ACTIVE);
		USER_DTO.setCreateDate(LocalDateTime.now());
		USER_DTO.setEmail(EMAIL);
		USER_DTO.setIdUser(ID);
		USER_DTO.setLastLogin(LocalDateTime.now());
		USER_DTO.setModified(LocalDateTime.now());
		USER_DTO.setName(NAME);
		USER_DTO.setPassword(PASSWORD);
		
		REQUEST_USER.setEmail(EMAIL);
		REQUEST_USER.setName(NAME);
		REQUEST_USER.setPassword(PASSWORD);
		REQUEST_USER.setPhones(LISTA_PHONES);
		
		
	}
	
	
	/**
	 * 
	 * Pruebas para el método SaveUser(RequestUser)
	 * 
	 * **/

	@Test
	public void saveUserServiceNoExisteTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findByEmail(EMAIL)).thenReturn(null);
		Mockito.when(iUserDataAcces.saveUser(USER_DTO)).thenReturn(ID);
		Mockito.when(iIPhoneDataAccess.saveAll(LISTA_PHONE)).thenReturn(RESPONSE_TRUE);
		usuarioServiceImpl.saveUser(REQUEST_USER);
	}
	
	@Test
	public void saveUserServiceExisteTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findByEmail(EMAIL)).thenReturn(USER_DTO);
		Mockito.when(iUserDataAcces.saveUser(USER_DTO)).thenReturn(ID);
		Mockito.when(iIPhoneDataAccess.saveAll(LISTA_PHONE)).thenReturn(RESPONSE_TRUE);
		usuarioServiceImpl.saveUser(REQUEST_USER);
	}
	
	@Test(expected = NullPointerException.class)
	public void saveUserServiceNullPointerExceptionTest() throws Exception, IllegalArgumentException, NullPointerException {
		Mockito.when(iUserDataAcces.findByEmail(EMAIL)).thenThrow(new NullPointerException());
		Mockito.when(iUserDataAcces.saveUser(USER_DTO)).thenThrow(new NullPointerException());
		Mockito.when(iIPhoneDataAccess.saveAll(new ArrayList<Phone>(0))).thenThrow(new NullPointerException());
		Mockito.when(iIPhoneDataAccess.saveAll(null)).thenReturn(RESPONSE_FALSE );
		Mockito.doThrow(NullPointerException.class).when(iUserDataAcces).saveUser(USER_DTO);
		usuarioServiceImpl.saveUser(REQUEST_USER);
		fail();
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void saveUserServiceIllegalArgumentExceptionTest() throws Exception, IllegalArgumentException, NullPointerException {
		Mockito.when(iUserDataAcces.saveUser(USER_DTO)).thenThrow(new IllegalArgumentException());
		Mockito.when(iUserDataAcces.findByEmail(EMAIL)).thenThrow(new IllegalArgumentException());
		Mockito.when(iIPhoneDataAccess.saveAll(new ArrayList<Phone>(0))).thenThrow(new IllegalArgumentException());
		Mockito.doThrow(IllegalArgumentException.class).when(iUserDataAcces).saveUser(USER_DTO);
		usuarioServiceImpl.saveUser(REQUEST_USER);
		fail();
	}

	
	@SuppressWarnings("static-access")
	@Test(expected = Exception.class)
	public void saveUserServiceIExceptionTest() throws Exception, IllegalArgumentException, NullPointerException {
		Mockito.doThrow(Exception.class).when(mapper).evaluarPhones(LISTA_PHONES, ID, PREDICATE);

		usuarioServiceImpl.saveUser(REQUEST_USER);
		fail();
	}
	
	
	
	/**
	 * 
	 * Pruebas para el método SaveUser(RequestUser)
	 * 
	 * **/
	@Test
	public void getUserByIdServiceExisteTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(USER_DTO);
		usuarioServiceImpl.getUserById(ID);
	}
	@Test(expected = IllegalArgumentException.class)
	public void getUserByIdServiceNoExisteTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(null);
		Mockito.doThrow(IllegalArgumentException.class).when(iUserDataAcces).findById(ID);
		usuarioServiceImpl.getUserById(ID);
		fail();
	}	
	@Test(expected = NullPointerException.class)
	public void getUserByIdServiceNullPointerExceptionTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(null);
		Mockito.doThrow(NullPointerException.class).when(iUserDataAcces).findById(ID);
		usuarioServiceImpl.getUserById(ID);
		fail();
	}
	@Test(expected = Exception.class)
	public void getUserByIdServiceExceptionTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(null);
		Mockito.doThrow(Exception.class).when(iUserDataAcces).findById(ID);
		usuarioServiceImpl.getUserById(ID);
		fail();
	}	
	
	/**
	 * 
	 * Pruebas para el método updateUser(REQUEST_USER,ID)
	 * 
	 * **/
	@Test
	public void updateUserServiceTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(USER_DTO);
		usuarioServiceImpl.updateUser(REQUEST_USER,ID);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateUserServiceIllegalArgumentExceptionTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(USER_DTO);
		Mockito.doThrow(IllegalArgumentException.class).when(iUserDataAcces).findById(ID);
		usuarioServiceImpl.updateUser(REQUEST_USER,ID);
		fail();
	}
	@Test(expected = NullPointerException.class)
	public void updateUserServiceINullPointerExceptionTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(USER_DTO);
		Mockito.doThrow(NullPointerException.class).when(iUserDataAcces).findById(ID);
		usuarioServiceImpl.updateUser(REQUEST_USER,ID);
		fail();
	}	

	@Test(expected = Exception.class)
	public void updateUserServiceExceptionTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(USER_DTO);
		Mockito.doThrow(Exception.class).when(iUserDataAcces).findById(ID);
		usuarioServiceImpl.updateUser(REQUEST_USER,ID);
		fail();
	}	
	
	
	
	
	/**
	 * 
	 * Pruebas para el método logOutUser(ID)
	 * 
	 * **/
	@Test
	public void logOutUserServiceTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUserDataAcces.findById(ID)).thenReturn(USER_DTO);
		
		Mockito.when(iUserDataAcces.saveUser(USER_DTO)).thenReturn(ID);
		usuarioServiceImpl.logOutUser(ID);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void logOutUserServiceIllegalArgumentExceptionTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.doThrow(IllegalArgumentException.class).when(iUserDataAcces).findById(ID);
		Mockito.doThrow(IllegalArgumentException.class).when(iUserDataAcces).saveUser(USER_DTO);
		usuarioServiceImpl.logOutUser(ID);
		fail();
	}
	
	@Test(expected = NullPointerException.class)
	public void logOutUserServiceNullPointerExceptionTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.doThrow(NullPointerException.class).when(iUserDataAcces).findById(ID);
		Mockito.doThrow(NullPointerException.class).when(iUserDataAcces).saveUser(USER_DTO);
		usuarioServiceImpl.logOutUser(ID);
		fail();
	}
	
	@Test(expected = Exception.class)
	public void logOutUserServiceExceptionTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.doThrow(Exception.class).when(iUserDataAcces).findById(ID);
		Mockito.doThrow(Exception.class).when(iUserDataAcces).saveUser(USER_DTO);
		usuarioServiceImpl.logOutUser(ID);
		fail();
	}
	
}
