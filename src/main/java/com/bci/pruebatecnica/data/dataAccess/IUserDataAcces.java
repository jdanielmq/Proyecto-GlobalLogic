package com.bci.pruebatecnica.data.dataAccess;


import com.bci.pruebatecnica.data.dto.UserDto;


public interface IUserDataAcces {
	
	public long saveUser(UserDto userDto) throws NullPointerException;
	
	public UserDto findByEmail(String email) throws NullPointerException;
	
	public UserDto findById(Long id) throws NullPointerException;
	
	

}
