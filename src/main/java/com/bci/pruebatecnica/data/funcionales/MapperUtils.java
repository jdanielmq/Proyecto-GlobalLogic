package com.bci.pruebatecnica.data.funcionales;

import org.modelmapper.ModelMapper;

public class MapperUtils {
	/**
	 *  Metodo que tranforma una clase Entity a clase DTO
	 *  
	 * @param Object
	 * @param DtoEntity
	 * @return DtoEntity
	 */	
	public static DtoEntity convertToDto(Object obj, DtoEntity mapper) {
		return new ModelMapper().map(obj, mapper.getClass());
	}
	
	/**
	 *  Metodo que transforma una clase DTO a Entity
	 *  
	 * @param Object
	 * @param DtoEntity
	 * @return Object
	 */	
	public static Object convertToEntity(Object obj, DtoEntity mapper) {
		return new ModelMapper().map(mapper, obj.getClass());
	}
}
