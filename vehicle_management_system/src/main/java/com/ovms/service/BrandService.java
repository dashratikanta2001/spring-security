package com.ovms.service;

import com.ovms.dto.BrandDto;
import com.ovms.response.CustomeResponse;

public interface BrandService {

	CustomeResponse<?> save(BrandDto brandDto);
	
	CustomeResponse<?> findAll();
	
}
