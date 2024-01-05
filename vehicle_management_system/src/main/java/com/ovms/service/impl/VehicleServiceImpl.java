package com.ovms.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ovms.dao.BrandsDao;
import com.ovms.dao.CustomerDao;
import com.ovms.dao.ShowroomDao;
import com.ovms.dao.VehicleDao;
import com.ovms.dto.VehicleDto;
import com.ovms.entity.Customer;
import com.ovms.entity.Showroom;
import com.ovms.entity.Vehicle;
import com.ovms.enums.VehicleType;
import com.ovms.exception.InvalidVehicleTypeException;
import com.ovms.response.CustomeResponse;
import com.ovms.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;

	@Autowired
	private BrandsDao brandsDao;

	@Autowired
	private ShowroomDao showroomDao;
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public CustomeResponse<VehicleDto> addNewVehicle(VehicleDto vehicleDto) {

		if (vehicleDto.getShowroom() == null || vehicleDto.getShowroom().getId() == 0) {
			throw new InvalidVehicleTypeException("Please enter showroom id.");
		}

		Showroom showroom = showroomDao.findById(vehicleDto.getShowroom().getId());
		if (showroom == null) {
			throw new InvalidVehicleTypeException("Showroom id " + vehicleDto.getShowroom().getId() + " not exist.");
		}

		String vehicleNumber = generateVehicleNumber(vehicleDao.countLastVehicleNumber(showroom),
				showroom.getBrand().getName(), showroom.getBrand().getVehicleType());
		System.out.println("Vehicle Number = " + vehicleNumber);

		if (vehicleDao.findByEngineAndChessis(vehicleDto.getEngineNumber(), vehicleDto.getChesisNumber()) != null) {
			throw new InvalidVehicleTypeException("Chesis number or engine number already exist.");
		}

		Vehicle vehicle = dtoToVehicle(vehicleDto);
		vehicle.setVehicleNumber(vehicleNumber);
		vehicle.setShowroom(showroom);
		vehicle.setAssignDate(new Date());

		Vehicle newVehicle = vehicleDao.addNewVehicle(vehicle);

		if (newVehicle != null) {
			return new CustomeResponse<>(vehicleToDto(newVehicle), HttpStatus.OK.value(), "Vehicle added.");
		}

		return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Vehicle not added.");

	}
	
	
	
	@Override
	public CustomeResponse<List<VehicleDto>> findByShowroomId(Integer id, Boolean registered) {
		// TODO Auto-generated method stub
		
		Showroom showroom = showroomDao.findById(id);
		if (showroom == null) {
			throw new InvalidVehicleTypeException("Showroom id " + id + " not exist.");
		}
		List<Vehicle> byShowroom = vehicleDao.findByShowroom(showroom, registered);
		
		if (!byShowroom.isEmpty()) {
			
			List<VehicleDto> vehicleDtos = byShowroom.stream().map(vehicle -> vehicleToDto(vehicle)).collect(Collectors.toList());
			
			return new CustomeResponse<>(vehicleDtos, HttpStatus.OK.value(), "Vehicle Found");
		}
		
		return new CustomeResponse<>(null, HttpStatus.OK.value(), "No Vehicle found");
	}
	


	@Override
	public CustomeResponse<VehicleDto> findByVehicleNumber(String vehicleNumber) {
		// TODO Auto-generated method stub
		Vehicle vehicle = vehicleDao.findByRegistrationNo(vehicleNumber);
		
		if (vehicle != null) {
			return new CustomeResponse<>(vehicleToDto(vehicle), HttpStatus.OK.value(), "Vehicle found");
		}
		
		return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Invalid vehicle number");
	}

	


	@Override
	public CustomeResponse<VehicleDto> registerVehicleToUser(Integer v_id, Integer u_id) {
		// TODO Auto-generated method stub
		
		Vehicle vehicle=vehicleDao.findById(v_id);
		
		if (vehicle== null) {
			throw new InvalidVehicleTypeException("Vehicle Number not found");
		}
		
		if (vehicle.getCustomer() !=null) {
			throw new InvalidVehicleTypeException("Vehicle is already registered for a Customer");
		}
		
		Customer customer = customerDao.findById(u_id);
		
		if (customer == null) {
			throw new InvalidVehicleTypeException("Customer not found");
		}
		
		vehicle.setRegDate(new Date());
		vehicle.setCustomer(customer);
		
		Vehicle registerVehicle = vehicleDao.RegisterVehicle(vehicle);
		
		return new CustomeResponse<>(vehicleToDto(registerVehicle), HttpStatus.OK.value(), "Vehicle found");
	}


	
	
	

	private String generateVehicleNumber(Integer lastNumber, String brand, VehicleType vehicleType) {
		final String firstName = "DEMO";
		int year = LocalDate.now().getYear();

		return firstName + "-" + brand.toUpperCase() + "-" + vehicleType.toString() + "-" + year + "-"
				+ (lastNumber + 1);
	}

	public Vehicle dtoToVehicle(VehicleDto vehicleDto) {
		Vehicle vehicle = new Vehicle();
		vehicle.setEngineNumber(vehicleDto.getEngineNumber().toUpperCase());
		vehicle.setChesisNumber(vehicleDto.getChesisNumber().toUpperCase());
		return vehicle;
	}

	public static VehicleDto vehicleToDto(Vehicle vehicle) {
		VehicleDto vehicleDto = new VehicleDto();

		vehicleDto.setId(vehicle.getId());
		vehicleDto.setVehicleNumber(vehicle.getVehicleNumber());
		vehicleDto.setEngineNumber(vehicle.getEngineNumber());
		vehicleDto.setChesisNumber(vehicle.getChesisNumber());
		vehicleDto.setAssignDate(vehicle.getAssignDate());
		vehicleDto.setRegDate(vehicle.getRegDate());

		if (vehicle.getShowroom()!=null) {
			vehicleDto.setShowroom(ShowroomServiceImpl.ShowroomToDto(vehicle.getShowroom()));			
		}
		
		if (vehicle.getCustomer() !=null) {
			vehicleDto.setCustomer(CustomerServiceImpl.CustomerToDto(vehicle.getCustomer()));			
		}
		return vehicleDto;
	}


}
