package com.bci.pruebatecnica.data.dataAccess.impl;


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
	
	@Autowired
	private IUsuarioDao iUsuarioDao;

	@Override
	@Transactional
	public long saveUser(UserDto userDto) throws DataAccessException {
		try {
			User user = (User)MapperUtils.convertToEntity(new User(),userDto);
			iUsuarioDao.save(user);
			return user.getIdUser();
		}catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findByEmail(String email) throws DataAccessException {
		try {
			User user = iUsuarioDao.findByEmail(email);
			if(user == null) 
				return null;
			
			return (UserDto)MapperUtils.convertToDto(user, new UserDto());

		}catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto findById(Long id) throws DataAccessException {
		try {
			User user = iUsuarioDao.findById(id).orElse(null);
			if(user == null) 
				return null;
			
			return (UserDto)MapperUtils.convertToDto(user, new UserDto());
		}catch (DataAccessException e) {
			throw e;
		}
	}

}
