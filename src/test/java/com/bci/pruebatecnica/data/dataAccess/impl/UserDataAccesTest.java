package com.bci.pruebatecnica.data.dataAccess.impl;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import com.bci.pruebatecnica.data.dto.UserDto;
import com.bci.pruebatecnica.data.entity.User;
import com.bci.pruebatecnica.data.funcionales.DtoEntity;
import com.bci.pruebatecnica.data.funcionales.MapperUtils;
import com.bci.pruebatecnica.data.repository.IUsuarioDao;

public class UserDataAccesTest {
	
	UserDto USER_DTO = new UserDto();
	private static final Long ID= 1L;  
	private static final String NAME = "Juan Daniel Muñoz Queupul";
	private static final String EMAIL = "juandaniel@hotmail.org";
	private static final String PASSWORD = "Hnnn23#Yn";
	private static final boolean ACTIVE = true;	
	
	private static final User USER = new  User();
	
	private static final Optional<User> OPTIONAL_USER = Optional.of(USER);
	private static final Optional<User> OPTIONAL_USER_EMPATY = Optional.empty();
	
	
	
	
	
	@Mock
	IUsuarioDao iUsuarioDao;
	
	@Mock
	DtoEntity DtoEntity;
	
	@Mock
	MapperUtils MapperUtils;
	
	@InjectMocks
	UserDataAcces userDataAcces;
	
	@Before
	public void init() throws Exception, IllegalArgumentException, DataAccessException{
		MockitoAnnotations.initMocks(this);
		
		USER_DTO.setActive(ACTIVE);
		USER_DTO.setCreateDate(LocalDateTime.now());
		USER_DTO.setEmail(EMAIL);
		USER_DTO.setIdUser(ID);
		USER_DTO.setLastLogin(LocalDateTime.now());
		USER_DTO.setModified(LocalDateTime.now());
		USER_DTO.setName(NAME);
		USER_DTO.setPassword(PASSWORD);

		USER.setActive(ACTIVE);
		USER.setCreateDate(LocalDateTime.now());
		USER.setEmail(EMAIL);
		USER.setIdUser(ID);
		USER.setLastLogin(LocalDateTime.now());
		USER.setModified(LocalDateTime.now());
		USER.setName(NAME);
		USER.setPassword(PASSWORD);
		
		
		
		
	}
	
	
	/**
	 * 
	 * Pruebas para el método Save(User)
	 * 
	 * **/

	@Test
	public void saveUserDataAccesTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUsuarioDao.save(Mockito.any(User.class))).thenReturn(USER);
		userDataAcces.saveUser(USER_DTO);
	}
	@Test(expected = NullPointerException.class)
	public void saveUserDataAccesNullPointerExceptionTest() throws NullPointerException{
		Mockito.when(iUsuarioDao.save(USER)).thenThrow(new NullPointerException());
		Mockito.doThrow(NullPointerException.class).when(iUsuarioDao).save(USER);
		userDataAcces.saveUser(USER_DTO);
	}	
	@Test(expected = DataAccessException.class)
	public void saveUserDataAccesExceptionTest() throws DataAccessException{
		Mockito.doThrow(DataAccessException.class).when(iUsuarioDao).save(USER);
		userDataAcces.saveUser(USER_DTO);
	}
	
	
	/**
	 * 
	 * Pruebas para el método Save(User)
	 * 
	 * **/

	@Test
	public void findByEmailDataAccesTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUsuarioDao.findByEmail(EMAIL)).thenReturn(USER);
		Mockito.when(iUsuarioDao.findByEmail(EMAIL)).thenReturn(null);
		userDataAcces.findByEmail(EMAIL);
	}
	@Test
	public void findByEmailDataAccesNullComunTest() throws NullPointerException{
		Mockito.when(MapperUtils.convertToDto(USER, USER_DTO)).thenReturn(null);
		userDataAcces.findByEmail(EMAIL);
	}
	
	@Test(expected = NullPointerException.class)
	public void findByEmailDataAccesNullPointerExceptionTest() throws  NullPointerException { 
		Mockito.doThrow(NullPointerException.class).when(MapperUtils).convertToDto(USER, USER_DTO);
		userDataAcces.findByEmail(EMAIL);
		fail();
	}
	@Test(expected = DataAccessException.class)
	public void findByEmailDataAccesDataAccessExceptionTest() throws DataAccessException {
		Mockito.doThrow(DataAccessException.class).when(iUsuarioDao).findByEmail(EMAIL);
		userDataAcces.findByEmail(EMAIL);
		fail();
	}
	
	
	/**
	 * 
	 * Pruebas para el método findById(ID)
	 * 
	 * **/
	@Test
	public void findByIdDataAccesTest() throws Exception, IllegalArgumentException, NullPointerException{
		Mockito.when(iUsuarioDao.findById(ID)).thenReturn(OPTIONAL_USER);
		Mockito.when(iUsuarioDao.findById(ID)).thenReturn(OPTIONAL_USER_EMPATY);
		userDataAcces.findById(ID);
	}
	

	@Test(expected = NullPointerException.class)
	public void findByEmailfindByIdDataAccesNullPointerExceptionTest() throws  NullPointerException { 
		Mockito.doThrow(NullPointerException.class).when(MapperUtils).convertToDto(USER, USER_DTO);
		userDataAcces.findById(ID);
		fail();
	}
	@Test(expected = DataAccessException.class)
	public void findByIdDataAccesDataAccessExceptionTest() throws DataAccessException {
		Mockito.doThrow(DataAccessException.class).when(iUsuarioDao).findById(ID);
		userDataAcces.findById(ID);
		fail();
	}

}
