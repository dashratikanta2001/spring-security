package com.ovms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ovms.dao.BrandsDao;
import com.ovms.dao.ShowroomDao;
import com.ovms.dto.ShowroomDto;
import com.ovms.entity.Brands;
import com.ovms.entity.Showroom;
import com.ovms.exception.InvalidVehicleTypeException;
import com.ovms.response.CustomeResponse;
import com.ovms.service.BrandService;
import com.ovms.service.ShowroomService;

@Service
public class ShowroomServiceImpl implements ShowroomService {

	@Autowired
	private ShowroomDao showroomDao;

	@Autowired
	private BrandsDao brandsDao;

	@Override
	public CustomeResponse<?> save(ShowroomDto showroomDto) {
		// TODO Auto-generated method stub

//		BrandServiceImpl brandServiceImpl = new BrandServiceImpl();
//		 Brands dtoToBrand = brandServiceImpl.dtoToBrand(showroomDto.getBrand());
//		
//		Brands brand = brandsDao.find(dtoToBrand);
//		

		Showroom showroom = showroomDao.save(dtoToShowroom(showroomDto));
		return new CustomeResponse<>(ShowroomToDto(showroom), HttpStatus.OK.value(), "Showroom added.");
	}

	@Override
	public CustomeResponse<?> findAll() {
		// TODO Auto-generated method stub
		List<Showroom> all = showroomDao.findAll();

		List<ShowroomDto> showroomDtos = all.stream().map(showroom -> ShowroomToDto(showroom))
				.collect(Collectors.toList());

		return new CustomeResponse<>(showroomDtos, HttpStatus.OK.value(), "Showroom found.");
	}
	
	

	@Override
	public CustomeResponse<?> findByVehicleType(String type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Showroom dtoToShowroom(ShowroomDto showroomDto) {
		// TODO Auto-generated method stub
		Showroom showroom = new Showroom();

		BrandServiceImpl brandServiceImpl = new BrandServiceImpl();
		Brands dtoToBrand = brandServiceImpl.dtoToBrand(showroomDto.getBrand());
		Brands brand = brandsDao.find(dtoToBrand);
		if (brand == null) {
			throw new InvalidVehicleTypeException("Invalid Vehicle type");
		}

		showroom.setAddress(showroomDto.getAddress());
		showroom.setEmail(showroomDto.getEmail());
		showroom.setName(showroomDto.getName());
		showroom.setPhoneNo(showroomDto.getPhoneNo());
		showroom.setBrand(brand);

		return showroom;
	}

	public ShowroomDto ShowroomToDto(Showroom showroom) {
		// TODO Auto-generated method stub

		ShowroomDto showroomDto = new ShowroomDto();
		
		BrandServiceImpl brandServiceImpl = new BrandServiceImpl();
		

		showroomDto.setId(showroom.getId());
		showroomDto.setAddress(showroom.getAddress());
		showroomDto.setEmail(showroom.getEmail());
		showroomDto.setName(showroom.getName());
		showroomDto.setPhoneNo(showroom.getPhoneNo());
//		System.out.println("Brand = "+showroom.getBrand());
		showroomDto.setBrand(brandServiceImpl.BrandToDto(showroom.getBrand()));

		return showroomDto;
	}


}
