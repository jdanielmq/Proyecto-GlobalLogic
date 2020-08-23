package com.bci.pruebatecnica.data.dataAccess.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.bci.pruebatecnica.data.dataAccess.IPhoneDataAccess;
import com.bci.pruebatecnica.data.entity.Phone;
import com.bci.pruebatecnica.data.repository.IPhoneDao;

@Component
public class PhoneDataAccess  implements IPhoneDataAccess {
	
	@Autowired
	private IPhoneDao iPhoneDao;

	@Override
	public boolean saveAll(List<Phone> listaPhones) throws DataAccessException {
		try {
			iPhoneDao.saveAll(listaPhones);
			return true;
		}catch (Exception e) {
			throw e;
		}
	}
}
