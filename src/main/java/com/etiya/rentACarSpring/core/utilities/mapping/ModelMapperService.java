package com.etiya.rentACarSpring.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forDto();

	ModelMapper forRequest();
}
