package com.bci.pruebatecnica.data.dataAccess.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bci.pruebatecnica.data.dataAccess.IPhoneDataAccess;
import com.bci.pruebatecnica.data.entity.Phone;
import com.bci.pruebatecnica.data.repository.IPhoneDao;

@Component
public class PhoneDataAccess  implements IPhoneDataAccess {
	
	private static final Logger logger = LoggerFactory.getLogger(PhoneDataAccess.class);
	
	@Autowired
	private IPhoneDao iPhoneDao;

	/**
	 *  Metodo donde se guarda el listado de telefonos del usuario
	 *  
	 * @param List<Phone>
	 * @return boolean
	 * @exception DataAccessException
	 */	
	@Override
	@Transactional
	public boolean saveAll(List<Phone> listaPhones) throws DataAccessException {
		try {
			iPhoneDao.saveAll(listaPhones);
			return true;
		}catch (DataAccessException e) {
			logger.error("ERROR - [PhoneDataAccess -> Metodo - saveAll] ", e);
			throw e;
		}
	}
}
