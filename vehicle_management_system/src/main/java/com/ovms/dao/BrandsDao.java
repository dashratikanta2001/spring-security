package com.ovms.dao;

import java.util.List;

import com.ovms.dto.BrandDto;
import com.ovms.entity.Brands;
import com.ovms.enums.VehicleType;

public interface BrandsDao {

	Brands save(Brands brands);
	
	List<Brands> allBrands();
	
	Brands find(Brands brands);
	
	List<Brands> findByvehicleType(VehicleType vehicleType);
	
}
