package com.bci.pruebatecnica.data.dataAccess.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bci.pruebatecnica.data.dataAccess.IUserDataAcces;
import com.bci.pruebatecnica.data.dto.UserDto;
import com.bci.pruebatecnica.data.entity.User;
import com.bci.pruebatecnica.data.funcionales.MapperUtils;
import com.bci.pruebatecnica.data.repository.IUsuarioDao;


@Component
public class UserDataAcces implements IUserDataAcces {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDataAcces.class);
	
	@Autowired
	private IUsuarioDao iUsuarioDao;

	/**
	 *  Metodo que recibe un objeto para transformalo a entidad y guardarlo
	 *  
	 * @param UserDto
	 * @return long
	 * @exception DataAccessException
	 */	
	@Override
	@Transactional
	public long saveUser(UserDto userDto) throws NullPointerException {
		try {
			User user = (User)MapperUtils.convertToEntity(new User(),userDto);
			iUsuarioDao.save(user);
			return user.getIdUser();
		}catch (DataAccessException e) {
			logger.error("ERROR - [UserDataAccess -> Metodo - saveUser] ", e);
			throw new NullPointerException(e.getMostSpecificCause().getMessage());
		}
	}
	
	/**
	 *  Metodo donde se busca por el atributo email en la base datos
	 *  si existe en la base de datos, el objeto se transforman en DTO y se retorna.
	 *  si no existe, retorna un null
	 *  
	 * @param String
	 * @return UserDto
	 * @exception DataAccessException
	 */	
	@Override
	@Transactional(readOnly = true)
	public UserDto findByEmail(String email) throws NullPointerException {
		try {
			User user = iUsuarioDao.findByEmail(email);
			if(user == null) 
				return null;
			
			return (UserDto)MapperUtils.convertToDto(user, new UserDto());

		}catch (DataAccessException e) {
			logger.error("ERROR - [UserDataAccess -> Metodo - findByEmail] ", e);
			throw new NullPointerException(e.getMostSpecificCause().getMessage());
		}
	}
	
	/**
	 *  Metodo donde se busca por el atributo id en la base datos
	 *  si existe en la base de datos, el objeto se transforman en DTO y se retorna.
	 *  si no existe, retorna un null
	 *  
	 * @param String
	 * @return UserDto
	 * @exception DataAccessException
	 */	
	@Override
	@Transactional(readOnly = true)
	public UserDto findById(Long id) throws NullPointerException {
		try {
			User user = iUsuarioDao.findById(id).orElse(null);
			if(user == null) 
				return null;
			
			return (UserDto)MapperUtils.convertToDto(user, new UserDto());
		}catch (DataAccessException e) {
			logger.error("ERROR - [UserDataAccess -> Metodo - findById] ", e);
			throw new NullPointerException(e.getMostSpecificCause().getMessage());
		}
	}

}
