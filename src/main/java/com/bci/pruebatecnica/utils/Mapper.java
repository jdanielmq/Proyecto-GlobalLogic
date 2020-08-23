package com.bci.pruebatecnica.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.bci.pruebatecnica.data.dto.RequestPhone;
import com.bci.pruebatecnica.data.entity.Phone;
import com.bci.pruebatecnica.data.entity.User;


public class Mapper {
	
	public static List<Phone> evaluarPhones(List<RequestPhone> listaPhoneDtos, Long idUser, Predicate<RequestPhone> predicate) {
		List<Phone> listaPhones = new ArrayList<>(0);
		for (RequestPhone rp : listaPhoneDtos) {
			if(predicate.test(rp)){
				Phone p = new Phone();
				p.setCountryCode(rp.getContryCode());
				p.setCityCode(rp.getCityCode());
				p.setNumber(rp.getNumber());
				p.setUser(new User(idUser));
				listaPhones.add(p);
			}
		}
		return listaPhones;
	}
}
