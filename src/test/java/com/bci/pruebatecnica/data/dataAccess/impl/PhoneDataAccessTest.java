package com.bci.pruebatecnica.data.dataAccess.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import com.bci.pruebatecnica.data.dataAccess.IPhoneDataAccess;
import com.bci.pruebatecnica.data.entity.Phone;
import com.bci.pruebatecnica.data.entity.User;
import com.bci.pruebatecnica.data.repository.IPhoneDao;

public class PhoneDataAccessTest {
	
	Phone PHONE = new Phone();
	private ArrayList<Phone> LISTA_PHONE = new ArrayList<Phone>();
	private static final Long ID= 1L;  
	private static final String NUMBER = "834567765";
	private static final String CITY_CODE = "524";
	private static final String COUNTRY_CODE = "52";
	
	User USER = new User(ID);
	private static final boolean VERDADERO = true;	
	private static final boolean FALSO = true;	
	
	
	
	@Mock
	IPhoneDao IPhoneDao;
	
	@InjectMocks
	PhoneDataAccess phoneDataAccess;
	
	@Before
	public void init() throws Exception, IllegalArgumentException, DataAccessException{
		MockitoAnnotations.initMocks(this);
		PHONE.setIdPhone(ID);
		PHONE.setCountryCode(COUNTRY_CODE);
		PHONE.setCityCode(CITY_CODE);
		PHONE.setNumber(NUMBER);
		PHONE.setUser(USER);
		
		LISTA_PHONE.add(PHONE);
		
		
	}
	
	/**
	 * 
	 * Pruebas para el método saveAll(List<Phone> listaPhones)
	 * 
	 * **/

	@Test
	public void saveAllPhoneDataAccesTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(IPhoneDao.saveAll(LISTA_PHONE)).thenReturn(new HashSet<Phone>());
		phoneDataAccess.saveAll(LISTA_PHONE);

	}
	
	@Test(expected = NullPointerException.class)
	public void saveAllPhoneDataAccesNullPointerExceptionTest() throws NullPointerException{
		Mockito.when(IPhoneDao.saveAll(LISTA_PHONE)).thenThrow(new NullPointerException());
		Mockito.doThrow(NullPointerException.class).when(IPhoneDao).saveAll((Iterable<Phone>)LISTA_PHONE);
		phoneDataAccess.saveAll(LISTA_PHONE);
		fail();
	}
	
	@Test(expected = DataAccessException.class)
	public void saveAllPhoneDataAccesDataAccessExceptionTest() throws DataAccessException{
		Mockito.doThrow(DataAccessException.class).when(IPhoneDao).saveAll((Iterable<Phone>)LISTA_PHONE);
		phoneDataAccess.saveAll(LISTA_PHONE);
		fail();
	}
	
	
	
	
	
}
