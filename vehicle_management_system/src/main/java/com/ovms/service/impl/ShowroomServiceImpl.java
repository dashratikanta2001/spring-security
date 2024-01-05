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
import com.ovms.enums.VehicleType;
import com.ovms.exception.InvalidVehicleTypeException;
import com.ovms.response.CustomeResponse;
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
		if (showroomDto.getVehicleType() != null && showroomDto.getVehicleBrand() != null) {
			VehicleType valueOfVehicleType;
			try {
				valueOfVehicleType = VehicleType.valueOf(VehicleType.class, showroomDto.getVehicleType().toUpperCase());

			} catch (Exception e) {
				throw new InvalidVehicleTypeException("Invalid Vehicle Type.");
			}
			Brands brands = new Brands();
			try {
				String vehicleBrand = showroomDto.getVehicleBrand();
				brands= brandsDao.find(vehicleBrand, valueOfVehicleType);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (brands == null) {
				throw new InvalidVehicleTypeException("Invalid vehicle Brand");
			}
//			showroom.setBrand(brands);
//			showroom.setVehicleType(brands.getVehicleType());
			Showroom dtoToShowroom = dtoToShowroom(showroomDto);
			dtoToShowroom.setBrand(brands);
			
			Showroom showroom = showroomDao.save(dtoToShowroom);
			if (showroom!=null) {
				return new CustomeResponse<>(ShowroomToDto(showroom), HttpStatus.OK.value(), "Showroom added.");				
			}
			
		}
		
		return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "showroom not added.");
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
		
		try {
			VehicleType valueOfVehicleType = VehicleType.valueOf(VehicleType.class, type.toUpperCase());
			List<Showroom> showroomList = showroomDao.findByType(valueOfVehicleType);
			
			List<ShowroomDto> showRoomDtos = showroomList.stream().map(list -> ShowroomToDto(list)).collect(Collectors.toList());
			return new CustomeResponse<>(showRoomDtos, HttpStatus.OK.value(), "Show room found");
		} catch (Exception e) {
			throw new InvalidVehicleTypeException("Invalid Vehicle Type.");
		}
		
	}

	
	@Override
	public CustomeResponse<?> findByVehicleBrand(String brand) {
		// TODO Auto-generated method stub
		
		Brands brands= brandsDao.findBrandName(brand);
		
		if (brands == null) {
			throw new InvalidVehicleTypeException("Invalid brand name");
		}
		
		List<Showroom> byBrand = showroomDao.findByBrand(brands.getName());
		
		List<ShowroomDto> showroomDtos = byBrand.stream().map(showroomBrand -> ShowroomToDto(showroomBrand)).collect(Collectors.toList());
		
				
		return new CustomeResponse<>(showroomDtos, HttpStatus.OK.value(), "Showroom found");
	}


	@Override
	public CustomeResponse<?> findByCity(String city) {
		// TODO Auto-generated method stub
		
		List<Showroom> showroomList = showroomDao.findByCity(city);
		
		List<ShowroomDto> showroomDtos = showroomList.stream().map(showroom -> ShowroomToDto(showroom)).collect(Collectors.toList());
		
		return new CustomeResponse<>(showroomDtos, HttpStatus.OK.value(), "Showroom found");
	}

	
	
	public static Showroom dtoToShowroom(ShowroomDto showroomDto) {
		// TODO Auto-generated method stub
		Showroom showroom = new Showroom();

		showroom.setName(showroomDto.getName());
		showroom.setAddress(showroomDto.getAddress());
		showroom.setPhoneNo(showroomDto.getPhoneNo());
		showroom.setEmail(showroomDto.getEmail());
//		showroom.setBrand(showroomDto.get);
		
//		if (showroomDto.getVehicleType() != null && showroomDto.getVehicleBrand() != null) {
//			VehicleType valueOfVehicleType;
//			try {
//				valueOfVehicleType = VehicleType.valueOf(VehicleType.class, showroomDto.getVehicleType().toUpperCase());
//
//			} catch (Exception e) {
//				throw new InvalidVehicleTypeException("Invalid Vehicle Type.");
//			}
//			Brands brands = new Brands();
//			try {
//				String vehicleBrand = showroomDto.getVehicleBrand();
//				brands= brandsDao.find(vehicleBrand, valueOfVehicleType);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			if (brands == null) {
//				throw new InvalidVehicleTypeException("Invalid vehicle Brand");
//			}
//			showroom.setBrand(brands);
////			showroom.setVehicleType(brands.getVehicleType());
//		}

		return showroom;
	}

	public static ShowroomDto ShowroomToDto(Showroom showroom) {
		// TODO Auto-generated method stub

		ShowroomDto showroomDto = new ShowroomDto();
		showroomDto.setId(showroom.getId());
		showroomDto.setName(showroom.getName());
		showroomDto.setAddress(showroom.getAddress());
		showroomDto.setPhoneNo(showroom.getPhoneNo());
		showroomDto.setEmail(showroom.getEmail());
		showroomDto.setVehicleBrand(showroom.getBrand().getName());
//		showroomDto.setVehicleType(showroom.getVehicleType().name());
		try
		{
		showroomDto.setVehicleType(showroom.getBrand().getVehicleType().name());
		}catch (Exception e) {
			// TODO: handle exception
		}

		return showroomDto;
	}

}
