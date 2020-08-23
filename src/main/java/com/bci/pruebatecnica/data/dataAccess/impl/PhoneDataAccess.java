package com.bci.pruebatecnica.data.dataAccess.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.bci.pruebatecnica.data.dataAccess.IPhoneDataAccess;
import com.bci.pruebatecnica.data.entity.Phone;
import com.bci.pruebatecnica.data.repository.IPhoneDao;

@Component
public class PhoneDataAccess  implements IPhoneDataAccess {
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneDataAccess.class);
	
	@Autowired
	private IPhoneDao iPhoneDao;

	@Override
	public boolean saveAll(List<Phone> listaPhones) throws DataAccessException {
		try {
			iPhoneDao.saveAll(listaPhones);
			return true;
		}catch (Exception e) {
			logger.error("ERROR - [PhoneDataAccess -> Metodo - saveAll] ", listaPhones);
			throw e;
		}
	}
}
