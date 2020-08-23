package com.bci.pruebatecnica.data.funcionales;

import org.modelmapper.ModelMapper;

public class MapperUtils {
	public static DtoEntity convertToDto(Object obj, DtoEntity mapper) {
		return new ModelMapper().map(obj, mapper.getClass());
	}

	public static Object convertToEntity(Object obj, DtoEntity mapper) {
		return new ModelMapper().map(mapper, obj.getClass());
	}
}
