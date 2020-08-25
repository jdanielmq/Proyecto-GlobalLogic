package com.bci.pruebatecnica.data.dataAccess;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bci.pruebatecnica.data.entity.Phone;

public interface IPhoneDataAccess {
	
	public boolean saveAll(List<Phone> listaPhones) throws NullPointerException;

}
