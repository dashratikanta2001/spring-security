package com.ovms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ovms.dao.BrandsDao;
import com.ovms.dto.BrandDto;
import com.ovms.entity.Brands;
import com.ovms.enums.VehicleType;
import com.ovms.exception.InvalidVehicleTypeException;
import com.ovms.response.CustomeResponse;
import com.ovms.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandsDao brandsDao;

	@Override
	public CustomeResponse<?> save(BrandDto brandDto) {
		// TODO Auto-generated method stub

		Brands brand = brandsDao.find(dtoToBrand(brandDto));
		if (brand == null) {
			Brands brands = brandsDao.save(dtoToBrand(brandDto));
			return new CustomeResponse<>(brands, HttpStatus.OK.value(), "Brand added successfully");

		}

		return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Brand already exist.");

	}

	@Override
	public CustomeResponse<?> findAll() {
		// TODO Auto-generated method stub
		List<Brands> allBrands = brandsDao.allBrands();

		List<BrandDto> allBrandDto = allBrands.stream().map(brand -> BrandToDto(brand)).collect(Collectors.toList());

		return new CustomeResponse<>(allBrandDto, HttpStatus.OK.value(), "Brand found");
	}

	public Brands dtoToBrand(BrandDto brandDto) {

		Brands brands = new Brands();

		brands.setName(brandDto.getName());

		try {
			brands.setVehicleType(VehicleType.valueOf(VehicleType.class, brandDto.getVehicleType().toUpperCase()));
			return brands;
		} catch (Exception e) {
			throw new InvalidVehicleTypeException("Invalid vehicle type");
		}
	}

	public BrandDto BrandToDto(Brands brand) {
		BrandDto brandDto = new BrandDto();

		brandDto.setId(brand.getId());
		brandDto.setName(brand.getName().toUpperCase());
		brandDto.setVehicleType(brand.getVehicleType().name());

		return brandDto;
	}

}
