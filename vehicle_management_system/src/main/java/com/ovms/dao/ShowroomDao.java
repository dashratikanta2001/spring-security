package com.ovms.dao;

import java.util.List;

import com.ovms.entity.Showroom;
import com.ovms.enums.VehicleType;

public interface ShowroomDao {

	Showroom save(Showroom showroom);
	
	Showroom findById(Integer id);
	
	Boolean doesShowroomExist(Showroom showroom);

	List<Showroom> findAll();

	List<Showroom> findByType(VehicleType type);

	List<Showroom> findByBrand(String brandName);
	
	List<Showroom> findByCity(String city);

	// Manajit told to not use this.
//	//@Query select * from  s Showroom  join b Brands on s.brandId = b.id where b.name =: brandName nativeQuery = true;
//	public List<Showroom> findByBrands (@param ("brandName")String brandName );

}
