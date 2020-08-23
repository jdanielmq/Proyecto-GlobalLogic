package com.bci.pruebatecnica.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.bci.pruebatecnica.data.entity.Phone;

public interface IPhoneDao extends CrudRepository<Phone, Long>{

}
