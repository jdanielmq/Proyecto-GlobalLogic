package com.bci.pruebatecnica.data.dataAccess;


import org.springframework.dao.DataAccessException;

import com.bci.pruebatecnica.data.dto.UserDto;


public interface IUserDataAcces {
	
	public long saveUser(UserDto userDto) throws DataAccessException;
	
	public UserDto findByEmail(String email) throws DataAccessException;
	
	public UserDto findById(Long id) throws DataAccessException;
	
	

}
