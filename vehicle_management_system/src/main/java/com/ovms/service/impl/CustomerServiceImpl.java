package com.ovms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ovms.dao.CridentialMasterDao;
import com.ovms.dao.CustomerDao;
import com.ovms.dao.RoleDao;
import com.ovms.dao.VehicleDao;
import com.ovms.dto.CustomerDto;
import com.ovms.dto.VehicleDto;
import com.ovms.entity.CridentialMaster;
import com.ovms.entity.Customer;
import com.ovms.entity.Role;
import com.ovms.entity.Vehicle;
import com.ovms.enums.RoleType;
import com.ovms.exception.InvalidVehicleTypeException;
import com.ovms.response.CustomeResponse;
import com.ovms.service.CustomerService;
import com.ovms.util.RegexPattern;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private CridentialMasterDao cridentialMasterDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public CustomeResponse<?> save(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		if (!RegexPattern.validEmailPattern(customerDto.getEmail())) {
			throw new InvalidVehicleTypeException("Please provide a valid email id.");
		}
		
		if (customerDao.findByEmail(customerDto.getEmail()) == null) {
			RegexPattern.validEmailPattern(customerDto.getEmail());
			RegexPattern.validPhoneNumberPattern(customerDto.getPhoneNo());
			Customer customer = dtoToCustomer(customerDto);
			
			Role role = roleDao.findRole(RoleType.ROLE_ADMIN);
			customer.setRole(role);
			Customer savedcustomer = customerDao.save(customer);
			
			String password = passwordEncoder.encode(customerDto.getEmail().substring(0, 4)+"@123");
			
			CridentialMaster cridentialMaster = new CridentialMaster(customerDto.getEmail(), password, customer);
			cridentialMasterDao.addCridential(cridentialMaster);
			
			return new CustomeResponse<>(CustomerToDto(savedcustomer), HttpStatus.OK.value(), "Customer added.");
		}

		return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Email id already exist.");
	}

	@Override
	public CustomeResponse<?> findByEmail(String email) {
		// TODO Auto-generated method stub

		Customer customer = customerDao.findByEmail(email);
		if (customer == null) {
			return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Email not found.");
		}
		return new CustomeResponse<>(CustomerToDto(customer), HttpStatus.OK.value(), "Customer found");
	}

	@Override
	public CustomeResponse<?> update(String email, CustomerDto customerDto) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.findByEmail(email);
		RegexPattern.validEmailPattern(customerDto.getEmail());
		RegexPattern.validPhoneNumberPattern(customerDto.getPhoneNo());

		if (customer == null) {
			return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Email not found");
		}

		customer.setName(customerDto.getName());
		customer.setAddress(customerDto.getAddress());
		customer.setPhoneNo(customerDto.getPhoneNo());

		if (!customer.getEmail().equals(customerDto.getEmail()) && customerDto.getEmail() != null
				&& !customerDto.getEmail().trim().isEmpty()) {
			throw new InvalidVehicleTypeException("Email can not be updated.");
		}

//		System.out.println("User id = " + customer.getId());

		customerDao.save(customer);
//		System.out.println("2 = User id = " + customer.getId());

		return new CustomeResponse<>(CustomerToDto(customer), HttpStatus.OK.value(), "Data updated.");
	}

	@Override
	public CustomeResponse<?> showAllVehicles(String email) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.findByEmail(email);
		if (customer == null) {
			return new CustomeResponse<>(null, HttpStatus.BAD_REQUEST.value(), "Email not found.");
		}

		List<Vehicle> vehicleList = vehicleDao.findByCustomer(customer);

		if (vehicleList.isEmpty()) {
			return new CustomeResponse<>(CustomerToDto(customer), HttpStatus.BAD_REQUEST.value(),
					"No vehicle found for the customer.");
		}

		List<VehicleDto> vehicleDtos = vehicleList.stream().map(vehicle -> VehicleServiceImpl.vehicleToDto(vehicle))
				.collect(Collectors.toList());

		CustomerDto customerDto = CustomerToDto(customer, vehicleDtos);

		return new CustomeResponse<>(customerDto, HttpStatus.OK.value(), "User found with vehicle");
	}

	public static Customer dtoToCustomer(CustomerDto customerDto) {
		Customer customer = new Customer(customerDto.getName(), customerDto.getEmail(), customerDto.getPhoneNo(),
				customerDto.getAddress());

		return customer;
	}

	public static CustomerDto CustomerToDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(),
				customer.getPhoneNo(), customer.getAddress());

		return customerDto;
	}

	private CustomerDto CustomerToDto(Customer customer, List<VehicleDto> vehicles) {

		CustomerDto customerDto = new CustomerDto();

		if (customer != null) {
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setEmail(customer.getEmail());
			customerDto.setAddress(customer.getAddress());
			customerDto.setPhoneNo(customer.getPhoneNo());
			customerDto.setVehicles(vehicles);

			return customerDto;
		}

		return null;
	}

}
