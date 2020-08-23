package com.bci.pruebatecnica.services;


import org.springframework.dao.DataAccessException;

import com.bci.pruebatecnica.data.dto.RequestUser;
import com.bci.pruebatecnica.data.dto.ResponseUser;

public interface IUsuarioService {
	
	public ResponseUser saveUser(RequestUser reqUser) throws Exception,IllegalArgumentException, DataAccessException;
	
	public ResponseUser getUserById(long id) throws Exception,IllegalArgumentException, DataAccessException;
	
	public boolean updateUser(RequestUser reqUser, long id) throws Exception,IllegalArgumentException, DataAccessException;
	
	public boolean logOutUser(long id) throws Exception,IllegalArgumentException, DataAccessException;
	
}
