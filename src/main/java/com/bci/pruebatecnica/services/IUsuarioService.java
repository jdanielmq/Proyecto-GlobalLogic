package com.bci.pruebatecnica.services;


import com.bci.pruebatecnica.data.dto.RequestUser;
import com.bci.pruebatecnica.data.dto.ResponseUser;

public interface IUsuarioService {
	
	public ResponseUser saveUser(RequestUser reqUser) throws Exception;
	
	public ResponseUser getUserById(long id) throws Exception;
	
	public boolean updateUser(RequestUser reqUser, long id) throws Exception;
	
	public boolean logOutUser(long id) throws Exception;
	
}
