package com.bci.pruebatecnica.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.bci.pruebatecnica.data.entity.User;

public interface IUsuarioDao extends CrudRepository<User, Long>{
	
	public User findByEmail(String email);
	

}
